package io.mklabs.hetznercloud.models

import java.net.URI

final case class HCloudHttpRequest(
                           scheme: String = "https",
                           host: String = "api.hetzner.cloud",
                           apiVersion: String = "v1",
                           token: String,
                     ) {

  def getEndpoint(resource: String): String = s"$scheme://$host/$apiVersion/$resource"

  def getUri(resource: String): URI = new URI(getEndpoint(resource))

  def getServersUri: URI = getUri("servers")

}
