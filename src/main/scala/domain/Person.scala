//package domain
//
//import slick.driver.H2Driver.api._
//import scalaz._, Scalaz._
//
///**
// * Person case class, stores information about people
// */
//case class Person(id: Option[Int], treeId: Int, firstName: Option[String], lastName: Option[String], email: String, gender: Option[String]) extends Identifiable {
//  def fullName: Option[String] = (firstName.getOrElse("") + " " + lastName.getOrElse("")).trim.some   // TODO: currently will return Some("") if (None, None), which is wrong => bisequence
//}
//
//class Persons(tag: Tag) extends Table[Person](tag, "Person") with IdentityColumn {
//  //  import domain.GenderConvert._
//
//  def treeId: Rep[Int] = column[Int]("treeId")
//  def firstName: Rep[Option[String]] = column[Option[String]]("firstName", O.Nullable)
//  def lastName: Rep[Option[String]] = column[Option[String]]("lastName", O.Nullable)
//  def email: Rep[String] = column[String]("email", O.Nullable)
//  def gender: Rep[Option[String]] = column[Option[String]]("gender")
//
//  def tree = foreignKey("PERSON_TREE_FK", treeId, TableQuery[FamilyTrees])(_.id)
//
//  def * = (id.?, treeId, firstName, lastName, email, gender) <> (Person.tupled, Person.unapply)
//}
