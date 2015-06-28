package domain

import domain.database.{DriverProvider, TableSchema, TableDefinition}
import slick.driver.JdbcDriver
import utils.Alias

import scalaz._
import Scalaz._

case class FamilyTree(id: Option[Int], label: String) extends Identifiable.Default[FamilyTree]{
  def withId(id: Int): FamilyTree = this.copy(id = id.some)
}

trait FamilyTreesTable extends TableDefinition with TableSchema with ExtensionSupport { provider: DriverProvider =>

  import driver.api._

  override type TableRecordType = FamilyTree

  override val table = TableQuery[TableDefinition]

  class TableDefinition(tag: Tag) extends EntityTable[FamilyTree](tag, "Tree") {
    def label: Rep[String] = column[String]("label")
    def * = (id.?, label) <> (FamilyTree.tupled, FamilyTree.unapply)
  }
}

object FamilyTrees {
  def apply(driver: JdbcDriver) = new FamilyTrees(driver)

  class FamilyTrees(val driver: JdbcDriver)
    extends FamilyTreesTable
    with FamilyTreesQuery
    with DriverProvider
}

trait FamilyTreesQuery extends CommonQuery { self: TableDefinition =>

}

trait CommonQuery { self: TableDefinition =>

}

