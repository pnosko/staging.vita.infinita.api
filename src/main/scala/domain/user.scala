package domain

import scala.slick.driver.H2Driver.simple._

/**
 * Todo case class, stores information about todo lists
 */

case class User(id: Option[Int], treeId: Int, name: String, personId: Int) extends Identifiable

/**
 * Slick Todo table definition
 */
class Users(tag: Tag) extends Table[User](tag, "User") with IdentityColumn {
  def name: Column[String] = column[String]("name", O.NotNull)
  def treeId: Column[Int] = column[Int]("treeId", O.NotNull)
  def personId: Column[Int] = column[Int]("personId", O.NotNull)

  def tree = foreignKey("TREE_FK", treeId, TableQuery[FamilyTrees])(_.id)
  def person = foreignKey("PERSON_FK", personId, TableQuery[Persons])(_.id)
  def * = (id.?, treeId, name, personId) <> (User.tupled, User.unapply _)
}
