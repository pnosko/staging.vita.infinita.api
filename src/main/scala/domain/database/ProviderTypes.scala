package domain.database

import domain.Identifiable

trait ProviderTypes { self: DriverProvider =>
  import self.driver.api._

  trait IdColumn[I] {
    def id: Rep[I]
  }

  trait IdentityColumn[Id] extends IdColumn[Id] { this: IdTable[_, Id] =>
    import self.driver.api._
    def id: Rep[Id] = column[Id]("id", O.AutoInc, O.PrimaryKey)
  }

  abstract class IdTable[M, I](tag: Tag, schemaName: Option[String], tableName: String)(implicit val colType: BaseColumnType[I])
    extends Table[M](tag, schemaName, tableName) with IdentityColumn[I] {
    def this(tag: Tag, tableName: String)(implicit mapping: BaseColumnType[I]) = this(tag, None, tableName)
  }

  type EntityTable[M <: Identifiable[M]] = IdTable[M, M#Id]
}
