package service

import core.DbService
import domain._
import core.DatabaseExt._
import scala.slick.driver.H2Driver.simple._

/**
 *
 */
trait UserService extends DbService {
  def getUsers: List[User] = db.withSession(implicit session => usersTable.list)
  def createUser(user: User): Option[User] = db.withSession(implicit session => usersTable.insertAndReturn(user))
  def getPersons(treeLabel: String): List[Person] = db.withSession { implicit session =>
    (for {
      p <- personsTable
      t <- treesTable.filter(_.label === treeLabel) if p.treeId === t.id
    } yield p).list
  }
  def createPerson(person: Person): Option[Person] = db.withSession(implicit session => personsTable.insertAndReturn(person))
}

object UserService extends UserService {

}

