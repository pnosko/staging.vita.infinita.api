package core

import com.typesafe.config.Config
import domain._
import scala.slick.driver.H2Driver.simple._
import scala.slick.jdbc.meta.MTable

import scalaz._
import Scalaz._

/**
 * Database configuration
 */
object DatabaseCfg {

  // For H2 in memory database we need to use DB_CLOSE_DELAY
  val db = Database.forURL("")

  // create TableQueries for all tables
  val usersTable: TableQuery[Users] = TableQuery[Users]
  val treesTable: TableQuery[FamilyTrees] = TableQuery[FamilyTrees]

  def create(implicit session:Session): Unit = {
    (treesTable.ddl ++ usersTable.ddl).create
  }

  def seed(implicit session:Session): Unit = {
    treesTable ++= Seq(FamilyTree(1.some, "1111"), FamilyTree(2.some, "2222"), FamilyTree(3.some, "3333"))

    usersTable ++= Seq(User(1.some, 1, "User Userowski"), User(2.some, 2, "Testo Testowski"), User(3.some, 3, "Marian Balko"))
  }

  // Initialize database if tables does not exists
  def init(config: Config) = {
    db.withTransaction { implicit session =>
      if (config. MTable.getTables("*").list.isEmpty) {
        seed
      }
//      if (MTable.getTables("Tree").list.isEmpty) {
//        treesTable.ddl.create
//      }
//      if (MTable.getTables("User").list.isEmpty) {
//        usersTable.ddl.create
//      }
    }
  }
}
