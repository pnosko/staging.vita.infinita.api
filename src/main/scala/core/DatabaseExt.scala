package core

import domain._

import scala.slick.driver.H2Driver
import scala.slick.driver.H2Driver.simple._
import scala.slick.lifted._

import scalaz._
import Scalaz._

/**
 *
 */

object DatabaseExt {
  // TODO: lens might be an overkill now, but consider in the future
  trait WithId[T] {
    def withId(entity: T, id: Int): T
  }

  object WithId {
    implicit val userWithId = new WithId[User] {
      def withId(user: User, id: Int): User = user.copy(id = id.some)
    }
    implicit val personWithId = new WithId[Person] {
      def withId(person: Person, id: Int): Person = person.copy(id = id.some)
    }
  }

  implicit class DatabaseInsertExt[ E <: AbstractTable[_] with IdentityColumn](query: H2Driver.simple.TableQuery[E])(implicit session: H2Driver.backend.Session) {
    // TODO: Can throw at the moment! Catch & wrap in validation or disjunction!
    def insertAndReturn(user: E#TableElementType)(implicit withId: WithId[E#TableElementType]): Option[E#TableElementType] ={
      val id = (query returning query.map(_.id)) += user
      withId.withId(user, id).some
    }

  }
}