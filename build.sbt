//********************************************************
// Play settings
//*************

name := """reactivemongo-silhouette-rest"""

version := "1.0-SNAPSHOT"

lazy val root = (project in file(".")).enablePlugins(PlayScala)

scalaVersion := "2.11.1"

libraryDependencies ++= Seq(
  jdbc,
  anorm,
  cache,
  ws,
  "org.reactivemongo"       %% "play2-reactivemongo"     % "0.10.5.0.akka23",
  "com.mohiva" %% "play-silhouette" % "2.0",
  "com.mohiva" %% "play-silhouette-testkit" % "2.0" % "test",
  "net.codingwell" %% "scala-guice" % "4.0.0-beta5"
)
 