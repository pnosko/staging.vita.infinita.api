package domain.database

import scala.concurrent.ExecutionContext

trait InsertionSupport { self: TableDefinition with ProviderTypes with DriverProvider =>
  import driver.api._

  type MapQuery = Query[Rep[Id], Id, Seq]

  val insertMapping: MapQuery

  def insertWith(selector: MapQuery)(entity: TableRecordType)(implicit ec: ExecutionContext) = {
    val action: driver.ReturningInsertActionComposer[TableRecordType, Id] = table.returning(selector)
    for {
      id <- action += entity
    } yield entity.withId(id)
  }

  def insert(entity: TableRecordType)(implicit ec: ExecutionContext) = insertWith(insertMapping)(entity)
}