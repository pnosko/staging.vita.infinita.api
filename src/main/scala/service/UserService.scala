package service

import domain._
import core.DatabaseExt._
import core.DatabaseCfg._
import scala.slick.driver.H2Driver.simple._


/**
 *
 */
trait UserService {
  def getUsers: List[User] = db.withSession(implicit session => usersTable.list)
  def createUser(user: User): Option[User] = db.withSession(implicit session => usersTable.insertAndReturn(user))
}

object UserService extends UserService {

}

