package service

import core._
import domain._
import scala.slick.driver.H2Driver.simple._
import DatabaseExt._

/**
 *
 */
trait PersonService extends DbService {
  def getPersons(treeId: Int): List[Person] = db.withSession { implicit session => personsTable.filter(_.treeId === treeId).list }
  def getPerson(treeId: Int, personId: Int): Option[Person] = db.withSession { implicit session => personsTable.filter(_.id === personId).firstOption }
  def createPerson(person: Person): Option[Person] = db.withSession(implicit session => personsTable.insertAndReturn(person))
}

object PersonService extends PersonService {

}
