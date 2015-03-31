package domain

import spray.json._
import de.heikoseeberger.akkahttpjsonspray.SprayJsonMarshalling

/**
 *
 */
trait Protocol extends DefaultJsonProtocol with SprayJsonMarshalling {
  import EnumProtocol._
  implicit val userFormat = jsonFormat3(User.apply)
  implicit val familyTreeFormat = jsonFormat2(FamilyTree.apply)
  implicit val personFormat = jsonFormat6(Person.apply)
}
