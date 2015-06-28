package domain.database

import slick.driver.JdbcDriver

trait DriverProvider {
  val driver: JdbcDriver
}


