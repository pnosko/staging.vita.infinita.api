package api

import akka.http.model._
import akka.http.testkit.ScalatestRouteTest
import domain._
import org.scalatest.{ Matchers, FlatSpec }

import scalaz._
import Scalaz._

/**
 * TodoApiSpec test class, checks if API calls return expected values
 */
class ServiceSpec extends FlatSpec with Matchers with ScalatestRouteTest with TestBootService with Protocol {
//  def testConfigSource = "akka.loglevel = WARNING"

  "Service" should "respond to single IP query" in {
    Get(s"/users}") ~> check {
      status shouldBe StatusCodes.OK
      contentType shouldBe ContentTypes.`application/json`
      responseAs[List[User]] shouldBe Nil
    }
  }
}