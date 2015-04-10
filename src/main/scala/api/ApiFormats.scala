package api

import spray.json.DefaultJsonProtocol
import de.heikoseeberger.akkahttpjsonspray.SprayJsonMarshalling._


/**
 *
 */
object ApiFormats {
  case class CreateUser(name: String, personId: Int)
  case class CreatePerson(treeId: Int, firstName: Option[String], lastName: Option[String], email: String, gender: Option[String])
}

trait ApiJsonFormats extends DefaultJsonProtocol {
  import ApiFormats._
  implicit val cuserFormat = jsonFormat2(CreateUser.apply)
  implicit val createPersonFormat = jsonFormat5(CreatePerson.apply)
}