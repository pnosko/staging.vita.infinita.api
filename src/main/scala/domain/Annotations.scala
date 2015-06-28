package domain

import java.sql.Timestamp

object Identifiable {
  trait Default[A <: Identifiable[A]] extends Identifiable[A] {
    type Id = Int
  }
}

trait Identifiable[E <: Identifiable[E]] {
  type Id
  def id: Option[E#Id]
  def withId(id: E#Id): E
}

trait Timestamped {
  def timestamp: Option[Timestamp]
}