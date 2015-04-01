package domain

import scala.slick.driver.H2Driver.simple._

/**
 * Todo case class, stores information about todo lists
 */

case class User(id: Option[Int], treeId: Int, name: String) extends Identifiable

/**
 * Slick Todo table definition
 */
class Users(tag: Tag) extends Table[User](tag, "User") with IdentityColumn {
  def name: Column[String] = column[String]("name", O.NotNull)
  def treeId: Column[Int] = column[Int]("treeId", O.NotNull)
  //def personId
  //def person
  def * = (id.?, treeId, name) <> (User.tupled, User.unapply _)
}
