package domain

import domain.database.{DriverProvider, TableSchema, TableDefinition}
import slick.driver.JdbcDriver

case class FamilyTree(id: Option[Int], label: String) extends Identifiable //TODO: change to uuid

trait FamilyTreesTable extends TableDefinition with TableSchema with ExtensionSupport {
  provider: DriverProvider =>

  import driver.api._

  override type TableRecordType = FamilyTree

  override val table = TableQuery[TableDefinition]

  class TableDefinition(tag: Tag) extends Table[FamilyTree](tag, "Tree") with IdentityColumn {
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


trait FamilyTreesQuery {

}

