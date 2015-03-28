package service

import domain._

//import spray.json.ProductFormats
import core.DatabaseCfg._
import scala.slick.driver.H2Driver.simple._

/**
 *
 */
trait UserService {
  def getUsers: List[User] = db.withSession(implicit session => usersTable.list)
}

object UserService extends UserService {

}
