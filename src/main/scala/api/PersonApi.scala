package api

import akka.http.server.Directives
import domain.Protocol
import service._

/**
 *
 */
class PersonApi extends Directives with DefaultTimeout with Protocol {

  import scala.concurrent.ExecutionContext.Implicits.global

  lazy val personService = PersonService

  lazy val routes =
    path("people" / Segment ) {treeLabel =>
      get {
        complete { personService.getPersons(treeLabel) }
      }
    }

}
