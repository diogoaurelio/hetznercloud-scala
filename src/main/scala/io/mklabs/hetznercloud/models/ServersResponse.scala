package io.mklabs.hetznercloud.models

import io.mklabs.hetznercloud.models.HPaginatedResponse.Meta

case class ServersResponse(servers: Seq[String], meta: Meta)
