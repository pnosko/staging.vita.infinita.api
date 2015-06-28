//package service
//
//import java.util.UUID
//
//import core.DbService
//import domain._
//import utils.Alias._
//
///**
// *
// */
//trait UserService extends DbService {
//  def createTree: Attempt[FamilyTree] = db.run(treesTable.insertAndReturn(FamilyTree(None, UUID.randomUUID().toString)))
//  def getUsers: List[User] = db.withSession(implicit session => usersTable.list)
//  def createUser(person: Person): Attempt[User] = db.withSession { implicit session =>
//    createTree.flatMap(t => usersTable.insertAndReturn(User(None, t.id.get, person.fullName.get, person.id.get)))
//  }
//
//  def createPerson(person: Person): Attempt[Person] = db.withSession(implicit session => personsTable.insertAndReturn(person))
//}
//
//object UserService extends UserService {
//
//}
//
