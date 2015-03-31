package domain

import scala.slick.driver.H2Driver.simple._
import core.DatabaseCfg._

/**
 * Tree case class, stores information about item
 */
case class FamilyTree(id: Option[Int], label: String) //TODO: change to uuid

/**
 * Slick Item table definition
 */
class FamilyTrees(tag: Tag) extends Table[FamilyTree](tag, "Tree") {
  def id: Column[Int] = column[Int]("id", O.AutoInc, O.NotNull, O.PrimaryKey)
  def label: Column[String] = column[String]("label", O.NotNull)
  def * = (id.?, label) <> (FamilyTree.tupled, FamilyTree.unapply _)
}
