package domain.database

import domain.Identifiable
import scala.concurrent.ExecutionContext
import scalaz._

trait TableDefinition extends ProviderTypes with InsertionSupport { provider: DriverProvider =>
  import slick.dbio._

  type TableRecordType <: Identifiable[TableRecordType]
  type TableDefinition <: EntityTable[TableRecordType]
  type Id = TableRecordType#Id

  import driver.api.TableQuery

  val table: TableQuery[TableDefinition]

  def batch[R <: TableRecordType, R2, F[_] : Foldable, E <: Effect](entities: F[R])(fn: (TableQuery[TableDefinition], R) => DBIOAction[R2, NoStream, E])(implicit m: Monoid[DBIOAction[R2, NoStream, E]]): DBIOAction[R2, NoStream, E] =
    Foldable[F].foldMap(entities) { r =>
      fn(table, r)
    }

  def insertOrUpdateBatch[R <: TableRecordType, F[_] : Foldable](entities: F[R])(implicit m: Monoid[DBIOAction[Int, NoStream, Effect.Write]]) =
    batch(entities) { (tbl, entity) =>
      import driver.api._
      tbl.insertOrUpdate(entity)
    }
}