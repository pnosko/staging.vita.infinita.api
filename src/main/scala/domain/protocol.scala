package domain

import api.ApiJsonFormats


/**
 *
 */
trait Protocol extends ApiJsonFormats {
  import EnumProtocol._
//  implicit val userFormat = jsonFormat4(User.apply)
//  implicit val familyTreeFormat = jsonFormat2(FamilyTree.apply)
//  implicit val personFormat = jsonFormat6(Person.apply)
////  implicit val fileFormat = jsonFormat6(File.apply)
//  implicit val relationshipFormat = jsonFormat5(Relationship.apply)
}
