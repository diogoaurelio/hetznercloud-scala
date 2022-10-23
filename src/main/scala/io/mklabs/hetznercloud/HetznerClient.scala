package io.mklabs.hetznercloud

import io.circe.Error
import io.circe.generic.auto._
import sttp.client3.basicRequest
import sttp.client3.circe.asJson
import sttp.model.Header
import sttp.model.MediaType
import sttp.client3.Empty
import sttp.client3.Identity
import sttp.client3.RequestT
import sttp.client3.Response
import sttp.client3.ResponseException
import sttp.client3.httpclient.zio.HttpClientZioBackend
import sttp.client3.logging.slf4j.Slf4jLoggingBackend
import sttp.model.Uri

import zio._

import io.mklabs.hetznercloud.models.HCloudHttpRequest
import io.mklabs.hetznercloud.models.ServersResponse

class HetznerClient(private val auth: HCloudHttpRequest) {

  def listServers: ZIO[Scope, Throwable, Response[Either[ResponseException[String, Error], ServersResponse]]] = {

    val request = getRequest
        .get(Uri(auth.getServersUri))
        .response(asJson[ServersResponse])

    fireRequest[ServersResponse](request)

  }

  private def getRequest: RequestT[Empty, Either[String, String], Any] = {
    basicRequest
        .header(Header.authorization("Bearer", auth.token))
        .header(Header.contentType(MediaType.ApplicationJson))
  }

  private def fireRequest[T](request: RequestT[Identity, Either[ResponseException[String, Error], T], Any],
              includeRequestTiming: Boolean = true,
              logRequestBody: Boolean = true,
              logResponseBody: Boolean = true,
             ): ZIO[Scope, Throwable, Response[Either[ResponseException[String, Error], T]]] = {
    HttpClientZioBackend.scoped().flatMap(backend => {
      val loggingBackend = Slf4jLoggingBackend(backend,
        includeTiming = includeRequestTiming,
        logRequestBody = logRequestBody,
        logResponseBody = logResponseBody,
      )
      for {
        response <- loggingBackend.send(request)
        _ <- Console.printLine(s"Got response code: ${response.code}")
        _ <- Console.printLine(s"response body ${response.body}")
      } yield response
    })
  }

}
