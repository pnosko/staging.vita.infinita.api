import sbt._

name          := """vita.infinita.api"""

version       := "0.1"

scalaVersion  := Version.scala

resolvers ++= Seq(
  "Scalaz Bintray Repo" at "http://dl.bintray.com/scalaz/releases",
  "Sonatype OSS Snapshots" at "https://oss.sonatype.org/content/repositories/snapshots",
  "hseeberger at bintray" at "http://dl.bintray.com/hseeberger/maven"
)

libraryDependencies ++= Dependencies.spraySlick

releaseSettings

scalariformSettings

Revolver.settings

fork in run := true
