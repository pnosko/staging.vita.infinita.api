package domain

import scala.slick.driver.H2Driver.simple._


/**
 *
 */

case class Credential(id: Option[Int], userId: Int) extends Identifiable

class Credentials(tag: Tag) extends Table[Credential](tag, "Credential") with IdentityColumn {
  override def id: Column[Int] = column[Int]("id", O.NotNull, O.AutoInc, O.PrimaryKey)
  def userId: Column[Int] = column[Int]("userId", O.NotNull)
  def user = foreignKey("USER_FK", userId, TableQuery[Users])(_.id)
  def * = (id.?, userId) <> (Credential.tupled, Credential.unapply _)
}
