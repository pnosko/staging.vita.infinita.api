package domain

import java.sql.Timestamp

trait Identifiable {
  def id: Option[Int]
}

trait Timestamped {
  def timestamp: Option[Timestamp]
}