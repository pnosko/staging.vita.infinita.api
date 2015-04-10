package domain

import scala.slick.driver.H2Driver.simple._

/**
 * Relationship case class
 */
case class Relationship(id: Option[Int], treeId: Int, fromId: Int, toId: Int, relationshipType: Option[Int]) extends Identifiable

class Relationships(tag: Tag) extends Table[Relationship](tag, "Relationship") with IdentityColumn {
  def treeId: Column[Int] = column[Int]("treeId", O.NotNull)
  def fromId: Column[Int] = column[Int]("fromId", O.NotNull)
  def toId: Column[Int] = column[Int]("toId", O.NotNull)
  def relationshipType: Column[Option[Int]] = column[Option[Int]]("relationshipType", O.Nullable)

  def * = (id.?, treeId, fromId, toId, relationshipType) <> (Relationship.tupled, Relationship.unapply _)
}