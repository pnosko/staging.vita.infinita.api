package api

import de.heikoseeberger.akkahttpjson4s
import de.heikoseeberger.akkahttpjson4s.Json4sSupport
import org.json4s.{DefaultFormats, Formats}


/**
 *
 */
object ApiFormats {
  case class CreateUser(name: String, personId: Int)
  case class CreatePerson(treeId: Int, firstName: Option[String], lastName: Option[String], email: String, gender: Option[String])
}

trait ApiJsonFormats extends Json4sSupport  {
  import ApiFormats._
  implicit def json4sFormats: Formats = DefaultFormats
//
//  implicit val cuserFormat = jsonFormat2(CreateUser.apply)
//  implicit val createPersonFormat = jsonFormat5(CreatePerson.apply)
}