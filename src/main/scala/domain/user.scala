package domain

import domain.database.{TableSchema, TableDefinition, DriverProvider}
import slick.driver.JdbcDriver
import scala.concurrent.ExecutionContext
import scalaz._
import Scalaz._

case class User(id: Option[Int], treeId: Int, name: String, personId: Int) extends Identifiable.Default[User] {
  def withId(id: Int) = this.copy(id = id.some)
}

trait UsersTable extends TableDefinition with TableSchema with ExtensionSupport { provider: DriverProvider =>
  import driver.api._

  override type TableRecordType = User

  override val table = TableQuery[TableDefinition]

  class TableDefinition(tag: Tag) extends EntityTable[User](tag, "User") {
    def name: Rep[String] = column[String]("name")
    def treeId: Rep[Int] = column[Int]("treeId")
    def personId: Rep[Int] = column[Int]("personId")

    def * = (id.?, treeId, name, personId) <> (User.tupled, User.unapply)
  }

  def inserts(entity: TableRecordType)(implicit ec: ExecutionContext) = for {
    id <- (table returning table.map(_.id)) += entity
  } yield entity.withId(id)
}

object Users {
  def apply(driver: JdbcDriver) = new Users(driver)

  class Users(val driver: JdbcDriver)
    extends UsersTable
    with UsersQuery
    with DriverProvider
}

trait UsersQuery {

}
