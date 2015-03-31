package api

import akka.http.model._
import akka.http.testkit.ScalatestRouteTest
import domain._
import org.scalatest.{ Matchers, FlatSpec }

import scalaz._
import Scalaz._

/**
 * UserApiSpec test class, checks if API calls return expected values
 */
class UserApiSpec extends FlatSpec with Matchers with ScalatestRouteTest with TestBootService with Protocol {

  "UserApi" should "respond with empty user list" in {
    Get(s"/users") ~> new UserApi().routes ~> check {
      status shouldBe StatusCodes.OK
      contentType shouldBe ContentTypes.`application/json`
      responseAs[List[User]] shouldBe Nil
    }
  }
}