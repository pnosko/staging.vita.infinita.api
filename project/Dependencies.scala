import sbt._

object Version {
  val akka           = "2.3.9"
  val akkaHttpExp    = "1.0-RC4"
  val spray          = "1.3.1"
  val scala          = "2.11.6"
  val slick          = "3.0.0"
  val c3p0           = "0.9.1.2"
  val h2Driver       = "1.4.185"
  val scalaTest      = "2.2.4"
  val spec2          = "3.2"
  val scalazSpec2    = "0.4.0-SNAPSHOT"
  val typesafeConfig = "1.2.1"
  val logback        = "1.1.2"
  val scalaz         = "7.1.3"
  val specs2         = "3.1.1"
}

object Library {
  val akkaActor      = "com.typesafe.akka"  %%  "akka-actor"        % Version.akka
  val akkaStream     = "com.typesafe.akka"  %   "akka-stream-experimental_2.11"       % Version.akkaHttpExp
  val akkaCore       = "com.typesafe.akka"  %   "akka-http-experimental_2.11"         % Version.akkaHttpExp
  val akkaHttp       = "com.typesafe.akka"  %   "akka-http-core-experimental_2.11"    % Version.akkaHttpExp
  val akkaHttpTest   = "com.typesafe.akka"  %   "akka-http-testkit-experimental_2.11" % Version.akkaHttpExp
  val akkaJson       = "de.heikoseeberger"  %%  "akka-http-json4s"                    % "0.9.1"
  val scalaz         = "org.scalaz"         %%  "scalaz-core"       % Version.scalaz
  val sprayJson      = "io.spray"           %%  "spray-json"        % "1.3.0"
  val slick          = "com.typesafe.slick" %%  "slick"             % Version.slick
  val c3p0           = "c3p0"               %   "c3p0"              % Version.c3p0
  val h2Database     = "com.h2database"     %   "h2"                % Version.h2Driver
  val akkaTestKit    = "com.typesafe.akka"  %%  "akka-testkit"      % Version.akka      % "test"
  val spec2          = "org.specs2"         %%  "specs2-core"       % Version.specs2    % "test"
  val scalaTest      = "org.scalatest"      %%  "scalatest"         % Version.scalaTest % "test"
  val logback        = "ch.qos.logback"     %   "logback-classic"   % Version.logback

}

object Dependencies {

  import Library._

  val spraySlick = Seq(
    akkaActor,
    akkaHttp,
    akkaCore,
    akkaStream,
    akkaHttpTest,
    akkaJson,
    scalaz,
    sprayJson,
    slick,
    c3p0,
    h2Database,
    akkaTestKit,
    scalaTest,
    spec2,
    logback
  )
}
