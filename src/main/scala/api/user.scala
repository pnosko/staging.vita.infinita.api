package api

import java.util.concurrent.TimeUnit

import akka.actor.ActorSystem
import akka.http.server._
import akka.util.Timeout
import domain._
import service._
import scalaz._
import Scalaz._

/**
 * API for User
 * Each api path is in separate spray 'path' directive, in my opinion is cleaner that nesting
 */
class UserApi(implicit val actorSystem: ActorSystem) extends Directives with DefaultTimeout with Protocol {

  import scala.concurrent.ExecutionContext.Implicits.global

  //  lazy val service = UserService

  lazy val routes =
    path("users") {
      get {
        complete { UserService.getUsers }
      }
      //        ~
      //        post {
      //          respondWithStatus(StatusCodes.Created) {
      //            handleWith { todo: User =>
      //              (todoActor ? CreateTodoList(todo)).mapTo[User]
      //            }
      //          }
      //        }
    }
  //  ~
  //        path("todo" / IntNumber) { listId =>
  //        get {
  //          complete {
  //            (todoActor ? GetItemsList(listId)).mapTo[Either[TodoListOperationError, List[Item]]]
  //          }
  //        } ~
  //          post {
  //            respondWithStatus(StatusCodes.Created) {
  //              handleWith { item: Item =>
  //                (todoActor ? InsertItem(item.copy(list = Some(listId)))).mapTo[Item]
  //              }
  //            }
  //          }
  //      } ~
  //      path("todo" / IntNumber / IntNumber) { (listId, itemId) =>
  //        delete {
  //          complete {
  //            (todoActor ? DeleteItem(listId, itemId)).mapTo[Either[TodoListOperationError, Int]]
  //              .map[HttpResponse] {
  //                case Left(error) => error match { // if call returns error, then we match error type to response code
  //                  case lde: UnknownTodoList =>
  //                    HttpResponse(status = StatusCodes.NotFound)
  //                  case ups: OperationNotSupported => HttpResponse(status = StatusCodes.NotImplemented,
  //                    entity = HttpEntity(ContentTypes.`application/json`, TodoListOperationErrorFormat.write(ups).toString))
  //                }
  //                case Right(dNum) => HttpResponse(status = StatusCodes.NoContent)
  //              }
  //          }
  //        }
  //      }

}

trait DefaultTimeout {
  implicit val timeout = Timeout(2, TimeUnit.SECONDS)
}