package core

import com.typesafe.config.Config
import domain._
import scala.slick.driver.H2Driver
import scala.slick.driver.H2Driver.simple._
import scala.slick.jdbc.meta.MTable

import scalaz._
import Scalaz._

/**
 * Database configuration
 */
object DatabaseCfg {

  // For H2 in memory database we need to use DB_CLOSE_DELAY
  var db: H2Driver.backend.DatabaseDef = null
  var config: Config = null

  // Initialize database if tables does not exists
  def init(config: Config) = {
    val env = config.getString("env.name")
    val dbUrl = config.getString(s"database.$env.url")
    this.db = Database.forURL(dbUrl)
    this.config = config

    val initializer = new DbInitializer
    initializer.init
  }
}

class DbInitializer extends DbService {
  def init: Unit = {
    db.withTransaction { implicit session =>
      if (MTable.getTables("*").list.isEmpty) {
        create
        if (config.getBoolean("database.seed"))
          seed
      }
    }
  }

  def create(implicit session:Session): Unit = {
    (treesTable.ddl ++ usersTable.ddl).create
  }

  def seed(implicit session:Session): Unit = {
    treesTable ++= Seq(FamilyTree(1.some, "1111"), FamilyTree(2.some, "2222"), FamilyTree(3.some, "3333"))
    personsTable ++= Seq(
      Person(1.some, 1, "User".some, "Userowski".some,  "user@userpwnage.com", Gender.Male.some)
    )
    usersTable ++= Seq(User(1.some, 1, "User Userowski"), User(2.some, 2, "Testo Testowski"), User(3.some, 3, "Marian Balko"))
  }
}
