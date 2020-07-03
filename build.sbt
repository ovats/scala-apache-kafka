name := """scala-apache-kafka"""

version := "1.0.2"

lazy val root = (project in file(".")).enablePlugins(PlayScala)

scalaVersion := "2.13.2"

libraryDependencies += guice
libraryDependencies += "org.scalatestplus.play" %% "scalatestplus-play" % "5.0.0" % Test
libraryDependencies += "org.apache.kafka" % "kafka-clients" % "2.4.1"
