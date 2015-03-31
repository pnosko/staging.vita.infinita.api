package domain

import spray.json._

/**
 *
 */
object EnumProtocol extends DefaultJsonProtocol {

//  abstract class EnumJsonFormat[T  <: Enumeration](val enum: T) extends JsonFormat[T] {
//    def write(enum: T): JsString = JsString(enum.toString)
//
//    def read(json: JsValue): enum.Value = json match {
//      case JsString(value) => enum.withName(value)
//      case _ => deserializationError("Enumeration expected")
//    }
//  }

//  implicit object QuestionTypesStatusJsonFormat extends EnumJsonFormat(Gender)
}
