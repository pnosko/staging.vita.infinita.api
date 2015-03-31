package core

import domain.{Persons, FamilyTrees, Users}

import scala.slick.lifted.TableQuery

/**
 * Trait to include in data accessing services
 */
trait DbService {
  lazy val db = DatabaseCfg.db
  lazy val config = DatabaseCfg.config

  val usersTable: TableQuery[Users] = TableQuery[Users]
  val treesTable: TableQuery[FamilyTrees] = TableQuery[FamilyTrees]
  val personsTable: TableQuery[Persons] = TableQuery[Persons]
}
