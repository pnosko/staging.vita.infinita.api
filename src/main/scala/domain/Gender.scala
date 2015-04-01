package domain

import scala.slick.driver.H2Driver.simple._

/**
 *
 */
//object Gender extends Enumeration {
//  type Gender = Value
//  val Male, Female, Other = Value
//}
//
//object GenderConvert {
//  import domain.Gender.Gender
//  implicit val genderTypeColumnType = MappedColumnType.base[Gender, String](
//    { g => g.toString },
//    { s => Gender.withName(s)}
//  )
//}
