//package core
//
//import domain._
//import utils.Alias._
//
//import slick.driver.H2Driver
//import slick.driver.H2Driver.api._
//import slick.lifted._
//
//import scalaz._
//import Scalaz._
//
///**
// *
// */
//
//object DatabaseExt {
//  // TODO: lens might be an overkill now, but consider in the future
//  trait WithId[T] {
//    def withId(entity: T, id: Int): T
//  }
//
//  object WithId {
//    implicit val userWithId = new WithId[User] {
//      def withId(user: User, id: Int): User = user.copy(id = id.some)
//    }
//    implicit val personWithId = new WithId[Person] {
//      def withId(person: Person, id: Int): Person = person.copy(id = id.some)
//    }
//    implicit val treeWithId = new WithId[FamilyTree] {
//      def withId(tree: FamilyTree, id: Int): FamilyTree = tree.copy(id = id.some)
//    }
//    implicit val relWithId = new WithId[Relationship] {
//      def withId(relationship: Relationship, id: Int): Relationship = relationship.copy(id = id.some)
//    }
//  }
//
//  implicit class DatabaseInsertExt[E <: AbstractTable[_] with IdentityColumn](query: H2Driver.api.TableQuery[E])(implicit session: H2Driver.backend.Session) {
//    // TODO: Can throw at the moment! Catch & wrap in validation or disjunction!
//    def insertAndReturn(entity: E#TableElementType)(implicit withId: WithId[E#TableElementType]): Attempt[E#TableElementType] = {
//      val res = \/.fromTryCatchThrowable[Int, Throwable](((query returning query.map(_.id)) += entity))
//        res.map(withId.withId(entity, _))
//    }
//
//  }
//}