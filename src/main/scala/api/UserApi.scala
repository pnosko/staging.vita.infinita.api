package api

import akka.actor.ActorSystem
import akka.http.server._
import domain._
import service._
import scalaz._
import Scalaz._

/**
 * API for User
 * Each api path is in separate spray 'path' directive, in my opinion is cleaner that nesting
 */
class UserApi(implicit val actorSystem: ActorSystem) extends ApiService {
  import scala.concurrent.ExecutionContext.Implicits.global

  lazy val userService = UserService

  lazy val routes =
    path("users") {
      get {
        complete { userService.getUsers }
      }
    }
}
