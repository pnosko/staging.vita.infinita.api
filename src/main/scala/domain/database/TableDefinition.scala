package domain.database

import scalaz.{Monoid, Foldable}

trait TableDefinition { provider: DriverProvider =>
  import slick.dbio._

  import driver.api.{Table, TableQuery}

  type TableRecordType
  type TableDefinition <: Table[TableRecordType]

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