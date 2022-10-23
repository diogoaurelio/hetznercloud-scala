package io.mklabs.hetznercloud

import zio.ZIOAppDefault
import zio._

import io.mklabs.hetznercloud.models.HCloudHttpRequest

object HCloudApp extends ZIOAppDefault {
  val token: String = sys.env("TOKEN")

  override val run: ZIO[Scope, Throwable, Any] = {
    val hCloudHttpRequest = HCloudHttpRequest(token = token)
    val client = new HetznerClient(hCloudHttpRequest)
    client.listServers
  }
}
