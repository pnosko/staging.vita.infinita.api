package domain

import scala.slick.lifted.Column
import scala.slick.driver.H2Driver.simple._

/**
 *
 */
trait IdentityColumn { this: Table[_] =>
  def id: Column[Int] = column[Int]("id", O.NotNull, O.AutoInc, O.PrimaryKey)
}

trait Identifiable {
  def id: Option[Int]
}
