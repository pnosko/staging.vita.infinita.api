package core

import domain._

/**
 * Trait to include in data accessing services
 */
trait DbService {
  lazy val db = DatabaseCfg.db
  lazy val config = DatabaseCfg.config
  lazy val driver = slick.driver.H2Driver

  val usersTable = Users(driver)
  val treesTable = FamilyTrees(driver)
//  val personsTable: TableQuery[Persons] = TableQuery[Persons]
//  val relationshipsTable: TableQuery[Relationships] = TableQuery[Relationships]
}
