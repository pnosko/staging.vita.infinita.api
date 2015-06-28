package domain.database
import scala.concurrent.ExecutionContext
import slick.jdbc.meta.MTable
import slick.profile.SqlProfile

trait TableSchema { provider: DriverProvider with TableDefinition =>
  import TableSchema.SchemaDbIoAction
  import slick.dbio.{Effect => GenEffect}

  import driver._
  import driver.api._

  lazy val schema: SchemaDescription = table.schema
  lazy val schemaName: Option[String] = table.baseTableRow.schemaName
  lazy val tableName: String = table.baseTableRow.tableName

  def createSchema(): SchemaDbIoAction[GenEffect.Schema] =
    schema.create

  def dropSchema(): SchemaDbIoAction[GenEffect.Schema] =
    schema.drop
}

object TableSchema {
  import slick.dbio._

  type SchemaDbIoAction[T <: Effect] = DBIOAction[Unit, NoStream, T]
  type SchemaDescription = SqlProfile#SchemaDescription

  def dbioActionZero[T <: Effect]: SchemaDbIoAction[T] =
    DBIO.successful(()).asInstanceOf[SchemaDbIoAction[T]]

  def create(tableSchemas: TableSchema*): SchemaDbIoAction[Effect.Schema] =
    create[Seq](tableSchemas)

  def create[M[T] <: TraversableOnce[T]](tableSchemas: M[TableSchema]): SchemaDbIoAction[Effect.Schema] =
    tableSchemas.foldLeft(dbioActionZero[Effect.Schema])(_ >> _.createSchema())

  def createIfNotExists(tableSchemas: TableSchema*)(implicit executor: ExecutionContext): SchemaDbIoAction[Effect.Read with Effect.Schema] =
    createIfNotExists[Seq](tableSchemas)

  def createIfNotExists[M[T] <: TraversableOnce[T]](tableSchemas: M[TableSchema])(implicit executor: ExecutionContext): SchemaDbIoAction[Effect.Read with Effect.Schema] =
    foldWithMTables(tableSchemas)(_.isEmpty, _.createSchema())

  def drop(tableSchemas: TableSchema*): SchemaDbIoAction[Effect.Schema] =
    drop[Seq](tableSchemas)

  def drop[M[T] <: TraversableOnce[T]](tableSchemas: M[TableSchema]): SchemaDbIoAction[Effect.Schema] =
    tableSchemas.foldLeft(dbioActionZero[Effect.Schema])(_ >> _.dropSchema())

  def dropIfExists(tableSchemas: TableSchema*)(implicit executor: ExecutionContext): SchemaDbIoAction[Effect.Read with Effect.Schema] =
    dropIfExists[Seq](tableSchemas)

  def dropIfExists[M[T] <: TraversableOnce[T]](tableSchemas: M[TableSchema])(implicit executor: ExecutionContext): SchemaDbIoAction[Effect.Read with Effect.Schema] =
    foldWithMTables(tableSchemas)(_.nonEmpty, _.dropSchema())

  def foldWithMTables[M[T] <: TraversableOnce[T]](tableSchemas: M[TableSchema])(predicate: Vector[MTable] => Boolean, fn: TableSchema => SchemaDbIoAction[Effect.Schema])(implicit executor: ExecutionContext): SchemaDbIoAction[Effect.Read with Effect.Schema] =
    tableSchemas.foldLeft(dbioActionZero[Effect.Read with Effect.Schema]) {
      case (actions, schema) =>
        actions >> MTable.getTables(schema.tableName).flatMap { tables =>
          //filter() gives a runtime error.
          //TODO: Reexamine after the next slick 3.x release.
          if (predicate(tables))
            fn(schema)
          else
            dbioActionZero[Effect.Schema]
        }
    }
}