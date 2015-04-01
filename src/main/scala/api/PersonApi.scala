package api

import akka.stream.ActorFlowMaterializer
import domain._
import service._

/**
 *
 */
class PersonApi(implicit val mat: ActorFlowMaterializer) extends ApiService {
  import scala.concurrent.ExecutionContext.Implicits.global

  lazy val personService = PersonService

  lazy val routes =
    pathPrefix("people") {
      (post & entity(as[Person])) { person =>
        complete {
          personService.createPerson(person)
        }
      } ~ (get & path(Segment)) { treeLabel =>
          complete { personService.getPersons(treeLabel) }
      }
    }
}
