package domain

import java.sql.Timestamp
import scala.slick.driver.H2Driver.simple._

//
///**
// * File case class, stores information about item
// */
//case class File(id: Option[Int], key: String, filename: String, path: String, fileType: Option[String], timestamp: Option[Timestamp]) extends Identifiable with Timestamped
//
//class Files(tag: Tag) extends Table[File](tag, "File") with IdentityColumn  with TimestampColumn {
//  def key: Column[String] = column[String]("key", O.NotNull)
//  def filename: Column[String] = column[String]("filename", O.NotNull)
//  def path: Column[String] = column[String]("path", O.NotNull)
//  def fileType: Column[Option[String]] = column[Option[String]]("fileType", O.Nullable)
//
//  def * = (id.?, key, filename, path, fileType, timestamp) <> (File.tupled, File.unapply _)
//}
