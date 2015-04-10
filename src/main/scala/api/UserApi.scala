package api

import akka.actor.ActorSystem
import akka.http.server._
import akka.stream.ActorFlowMaterializer
import domain._
import service._
import scalaz._
import Scalaz._

/**
 * API for User
 * Each api path is in separate spray 'path' directive, in my opinion is cleaner that nesting
 */
class UserApi(implicit val materializer: ActorFlowMaterializer) extends ApiService with ApiJsonFormats {
  import scala.concurrent.ExecutionContext.Implicits.global
  import ApiFormats._

  lazy val userService = UserService

  lazy val routes =
    path("users") {
      get {
        complete { userService.getUsers }
      } ~ (post & entity(as[CreateUser])) { case CreateUser(name, personId) =>
        complete {
          null
        }
      }
    }
}


