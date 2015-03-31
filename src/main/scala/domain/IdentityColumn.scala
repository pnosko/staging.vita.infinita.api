package domain

import scala.slick.lifted.Column

/**
 *
 */
trait IdentityColumn { //this: RelationalTableComponent.Table =>
  def id: Column[Int] //= column[Int]("id", O.NotNull, O.AutoInc, O.PrimaryKey)
}

trait Identifiable {
  def id: Option[Int]
}
