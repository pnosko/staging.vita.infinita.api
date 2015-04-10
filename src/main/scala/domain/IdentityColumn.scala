package domain

import java.sql.Timestamp
import java.time.Instant

import scala.slick.lifted.Column
import scala.slick.driver.H2Driver.simple._

import scalaz._
import Scalaz._

/**
 *
 */
trait IdentityColumn { this: Table[_] =>
  def id: Column[Int] = column[Int]("id", O.NotNull, O.AutoInc, O.PrimaryKey)
}

trait TimestampColumn { this: Table[_] =>
  def timestamp: Column[Option[Timestamp]] = column[Option[Timestamp]]("timestamp", O.Default(Timestamp.from(Instant.now()).some), O.Nullable)
}

trait Identifiable {
  def id: Option[Int]
}

trait Timestamped {
  def timestamp: Option[Timestamp]
}
