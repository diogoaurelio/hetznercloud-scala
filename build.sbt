
ThisBuild / scalaVersion := "2.13.10"
ThisBuild / version          := "0.1.0-SNAPSHOT"
ThisBuild / organization     := "io.mklabs"
ThisBuild / organizationName := "mklabs"

val sttpClientVersion = "3.8.3"
val slf4jVersion = "2.0.3"

lazy val root = (project in file("."))
  .settings(
    name := "hetznercloud-scala",
    libraryDependencies ++= Seq(
      "com.softwaremill.sttp.client3" %% "zio" % sttpClientVersion,
      "com.softwaremill.sttp.client3" %% "circe" % sttpClientVersion,
      "io.circe" %% "circe-generic" % "0.14.3",

      "com.softwaremill.sttp.client3" %% "slf4j-backend" % sttpClientVersion,
      "org.slf4j" % "slf4j-api" % slf4jVersion,
      "org.slf4j" % "slf4j-simple" % slf4jVersion,

    ),
  )
