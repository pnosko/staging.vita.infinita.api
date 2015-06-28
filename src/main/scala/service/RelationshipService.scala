//package service
//
//import core.DbService
//import domain._
//import utils.Alias._
//import core.DatabaseExt._
//import scala.slick.driver.H2Driver.simple._
//
///**
// *
// */
//class RelationshipService extends DbService {
//  def createRelationship(treeId: Int, fromId: Int, toId: Int): Attempt[Relationship] = db.withSession(implicit session => relationshipsTable.insertAndReturn(Relationship(None, treeId, fromId, toId, None)))
//  def getRelationships(treeId: Int): List[Relationship] = db.withSession(implicit session => relationshipsTable.filter(_.treeId === treeId).list)
//}
//
//object RelationshipService extends RelationshipService {
//
//}
//
