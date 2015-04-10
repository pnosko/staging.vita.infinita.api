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
    val dbUrl = config.getString("application.database.url")
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
        if (config.getBoolean("application.database.seed"))
          seed
      }
    }
  }

  def create(implicit session: Session): Unit = {
    (usersTable.ddl ++ treesTable.ddl ++ personsTable.ddl ++ relationshipsTable.ddl).create
  }

  def seed(implicit session: Session): Unit = {
    treesTable ++= Seq(
      FamilyTree(1.some, "1111"),
      FamilyTree(2.some, "2222"),
      FamilyTree(3.some, "3333")
    )

    personsTable ++= Seq(
      Person(1.some, 1, "User".some, "Userowski".some, "user@userpwnage.com", "M".some),
      Person(2.some, 1, "Jan".some, "Smrek".some, "a@b.com",  "M".some),
      Person(3.some, 1, "Margita".some, "Figuli".some, "a@b.com",  "F".some),
      Person(4.some, 1, "Mark".some, "Zukorberg".some, "a@b.com",  "M".some),
      Person(5.some, 1, "Ayn".some, "Rand".some, "a@b.com",  "F".some),
      Person(6.some, 1, "Ferko".some, "Kalerab".some, "a@b.com",  "M".some),
      Person(7.some, 1, "Paul".some, "Dirac".some, "a@b.com",  "M".some),
      Person(8.some, 1, "Erwin".some, "Schroedinger".some, "a@b.com",  "M".some),
      Person(9.some, 1, "Maria".some, "Curie-Sklodowska".some, "a@b.com",  "F".some),
      Person(10.some, 1, "Testo".some, "Testowski".some, "a@b.com",  "M".some),
      Person(11.some, 1, "Marian".some, "Balko".some, "a@b.com",  "M".some)
    )

    usersTable ++= Seq(
      User(1.some, 1, "User Userowski", 1),
      User(2.some, 2, "Testo Testowski", 10),
      User(3.some, 3, "Marian Balko", 11)
    )

    relationshipsTable ++= Seq(
      Relationship(1.some, 1, 1, 2, None),
      Relationship(1.some, 1, 1, 3, None),
      Relationship(1.some, 1, 4, 3, None),
      Relationship(1.some, 1, 3, 5, None),
      Relationship(1.some, 1, 6, 8, None),
      Relationship(1.some, 1, 6, 7, None),
      Relationship(1.some, 1, 9, 7, None),
      Relationship(1.some, 1, 9, 8, None)
    )

  }
}
