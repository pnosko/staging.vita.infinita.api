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
class UserApiSpec extends FlatSpec with Matchers with ScalatestRouteTest with TestBootService with Protocol with ApiJsonFormats {
  implicit val m = this.materializer
  val api = new UserApi()
  val mockery = new Mockery()

  it should "respond with empty user list" in {
    Get("/users") ~> api.routes ~> check {
      status shouldBe StatusCodes.OK
      contentType shouldBe ContentTypes.`application/json`
      responseAs[List[User]] shouldBe Nil
    }
  }

  it should "create user with necessary data" in {
    val person = mockery.createDummyPerson
    Post("/users", ApiFormats.CreateUser("name", person.id.get)) ~> api.routes ~> check {
      status shouldBe StatusCodes.OK
      contentType shouldBe ContentTypes.`application/json`
      responseAs[User] shouldBe User(1.some, 1, "name", person.id.get)
    }
  }
}