package service

import core._
import domain._
import scala.slick.driver.H2Driver.simple._
import DatabaseExt._

/**
 *
 */
trait PersonService extends DbService {
  def getPersons(treeLabel: String): List[Person] = db.withSession { implicit session =>
    (for {
      p <- personsTable
      t <- treesTable.filter(_.label === treeLabel) if p.treeId === t.id
    } yield p).list
  }
  def createPerson(person: Person): Option[Person] = db.withSession(implicit session => personsTable.insertAndReturn(person))
}

object PersonService extends PersonService {

}
