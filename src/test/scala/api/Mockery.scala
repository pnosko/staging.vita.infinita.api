package api

import domain.Person
import service.UserService

import scalaz._
import Scalaz._

/**
 *
 */
class Mockery {
  val userSvc = UserService
  def createDummyPerson: Person = {
    val p = Person(None, 1, "Jaroslav".some, "Siska".some, "jaro.siska@journi.com", "M".some)
    userSvc.createPerson(p).toOption.get
  }
}
