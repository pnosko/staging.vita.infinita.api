package domain

import java.sql.Timestamp
import java.time.Instant

import domain.database.DriverProvider

import scalaz._
import Scalaz._

trait ExtensionSupport { self: DriverProvider =>
  import driver.api._



  trait TimestampColumn { this: self.driver.api.Table[_] =>
    def timestamp: Rep[Option[Timestamp]] = column[Option[Timestamp]]("timestamp", O.Default(Timestamp.from(Instant.now()).some))
  }
}