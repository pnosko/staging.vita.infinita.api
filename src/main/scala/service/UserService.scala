package service

import java.util.UUID

import core.DbService
import domain._
import core.DatabaseExt._
import scala.slick.driver.H2Driver.simple._

/**
 *
 */
trait UserService extends DbService {
  def createTree: Option[FamilyTree] = db.withSession(implicit session => treesTable.insertAndReturn(FamilyTree(None, UUID.randomUUID().toString)))
  def getUsers: List[User] = db.withSession(implicit session => usersTable.list)
  def createUser(person: Person): Option[User] = db.withSession { implicit session =>
    createTree.flatMap(t => usersTable.insertAndReturn(User(None, t.id.get, person.fullName.get, person.id.get)))
  }

  def createPerson(person: Person): Option[Person] = db.withSession(implicit session => personsTable.insertAndReturn(person))
}

object UserService extends UserService {

}

