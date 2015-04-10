package domain

import scala.slick.driver.H2Driver.simple._

/**
 * Tree case class, stores information about item
 */
case class FamilyTree(id: Option[Int], label: String) extends Identifiable//TODO: change to uuid

/**
 * Slick Item table definition
 */
class FamilyTrees(tag: Tag) extends Table[FamilyTree](tag, "Tree") with IdentityColumn {
  def label: Column[String] = column[String]("label", O.NotNull)
  def * = (id.?, label) <> (FamilyTree.tupled, FamilyTree.unapply _)
}
