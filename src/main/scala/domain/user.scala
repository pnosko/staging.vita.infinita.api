package domain

import scala.slick.driver.H2Driver.simple._

/**
 * Todo case class, stores information about todo lists
 */

case class User(id: Option[Int], name: String, treeId: Int /*, personId: Int*/ )

/**
 * Slick Todo table definition
 */
class Users(tag: Tag) extends Table[User](tag, "User") {

  def id: Column[Int] = column[Int]("id", O.NotNull, O.AutoInc, O.PrimaryKey)
  def name: Column[String] = column[String]("name", O.NotNull)
  def treeId: Column[Int] = column[Int]("treeId", O.NotNull)
  //def personId
  //def person
  def * = (id.?, name, treeId) <> (User.tupled, User.unapply _)
}
