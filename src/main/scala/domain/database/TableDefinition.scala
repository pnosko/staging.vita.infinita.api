package domain.database

import domain.{Identifiable, ExtensionSupport}
import utils.Alias._

import scala.concurrent.ExecutionContext
import scalaz.{-\/, \/, Monoid, Foldable}

trait TableDefinition extends ProviderTypes { provider: DriverProvider =>
  import slick.dbio._

  import driver.api.{TableQuery}

  type TableRecordType <: Identifiable[TableRecordType]
  type TableDefinition <: EntityTable[TableRecordType]

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

  val sth = driver.api.jdbcActionExtensionMethods()
  def insert(entity: TableRecordType)(implicit ec: ExecutionContext) = for {
    id <- ((table returning table.map(_.id)) += entity)
  } yield entity.withId(id)
}