package domain

import spray.json.DefaultJsonProtocol
import de.heikoseeberger.akkahttpjsonspray.SprayJsonMarshalling

/**
 *
 */
trait Protocol extends DefaultJsonProtocol with SprayJsonMarshalling {
  import GenderConvert._
  implicit val userFormat = jsonFormat3(User.apply)
  implicit val familyTreeFormat = jsonFormat2(FamilyTree.apply)
  implicit val personFormat = jsonFormat5(Person.apply)
}
