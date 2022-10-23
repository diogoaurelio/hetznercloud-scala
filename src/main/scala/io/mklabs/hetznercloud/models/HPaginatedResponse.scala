package io.mklabs.hetznercloud.models

object HPaginatedResponse {
  case class Meta(pagination: Pagination)
  case class Pagination(page: Int, per_page: Int, previous_page: Option[Int], next_page: Option[String], last_page: Int, total_entries: Int)
}
