package domain

//import akka.http.marshalling.DefaultJsonProtocol
import de.heikoseeberger.akkahttpjsonspray.SprayJsonMarshalling
import spray.json.DefaultJsonProtocol

/**
 *
 */
trait Protocol extends DefaultJsonProtocol with SprayJsonMarshalling {
  import EnumProtocol._
  implicit val userFormat = jsonFormat4(User.apply)
  implicit val familyTreeFormat = jsonFormat2(FamilyTree.apply)
  implicit val personFormat = jsonFormat6(Person.apply)
//  implicit val fileFormat = jsonFormat6(File.apply)
  implicit val relationshipFormat = jsonFormat5(Relationship.apply)
}
