package utils

import akka.util.Timeout
import scala.concurrent.duration._
import scala.language.postfixOps

trait ImplicitTimeout {
  implicit val timeout = Timeout(10 seconds)
}