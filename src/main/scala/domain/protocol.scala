package domain

import spray.json.DefaultJsonProtocol
import de.heikoseeberger.akkahttpjsonspray.SprayJsonMarshalling

/**
 *
 */
trait Protocol extends DefaultJsonProtocol with SprayJsonMarshalling {
  implicit val userFormat = jsonFormat3(User.apply)
  implicit val familyTreeFormat = jsonFormat2(FamilyTree.apply)
}
