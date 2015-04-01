package domain

//import domain.Gender.Gender

import scala.slick.driver.H2Driver.simple._

/**
 * Person case class, stores information about people
 */
case class Person(id: Option[Int], treeId: Int, firstName: Option[String], lastName: Option[String], email: String, gender: Option[String]) extends Identifiable

/**
 * Slick Todo table definition
 */
class Persons(tag: Tag) extends Table[Person](tag, "Person") with IdentityColumn {
  //  import domain.GenderConvert._

  def treeId: Column[Int] = column[Int]("treeId", O.NotNull)
  def firstName: Column[Option[String]] = column[Option[String]]("firstName", O.Nullable)
  def lastName: Column[Option[String]] = column[Option[String]]("lastName", O.Nullable)
  def email: Column[String] = column[String]("email", O.Nullable)
  def gender: Column[Option[String]] = column[Option[String]]("gender")

  def tree = foreignKey("TREE_FK", treeId, TableQuery[FamilyTrees])(_.id)

  def * = (id.?, treeId, firstName, lastName, email, gender) <> (Person.tupled, Person.unapply _)
}
