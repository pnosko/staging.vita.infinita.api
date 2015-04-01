package domain

//import akka.http.marshalling.DefaultJsonProtocol
import de.heikoseeberger.akkahttpjsonspray.SprayJsonMarshalling
import spray.json.DefaultJsonProtocol

/**
 *
 */
trait Protocol extends DefaultJsonProtocol with SprayJsonMarshalling {
  import EnumProtocol._
  implicit val userFormat = jsonFormat3(User.apply)
  implicit val familyTreeFormat = jsonFormat2(FamilyTree.apply)
  implicit val personFormat = jsonFormat6(Person.apply)
}
