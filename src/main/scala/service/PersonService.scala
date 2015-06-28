//package service
//
//import core._
//import domain._
//import utils.Alias._
//import slick.driver.H2Driver.api._
//import DatabaseExt._
//
//import scala.concurrent.Future
//
///**
// *
// */
//trait PersonService extends DbService {
//  def getPersons(treeId: Int): Future[Seq[Person]] = db.run(personsTable.filter(_.treeId === treeId).result)
//  def getPerson(treeId: Int, personId: Int): Future[Person] = db.run(personsTable.filter(_.id === personId).head)
//  def createPerson(person: Person): Attempt[Person] = db.withSession( implicit session => personsTable.insertAndReturn(person))
//}
//
//object PersonService extends PersonService {
//
//}
