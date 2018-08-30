name := "samples-ignite-hadoop-spark"

version := "0.1"

scalaVersion := "2.11.12"

val igniteVersion = "2.6.0"

libraryDependencies += "org.scala-sbt" %% "command" % "0.99.4"

libraryDependencies += "org.apache.ignite" % "ignite-core" % igniteVersion
libraryDependencies += "org.apache.ignite" % "ignite-spark" % igniteVersion
libraryDependencies += "org.apache.ignite" % "ignite-hadoop" % igniteVersion

libraryDependencies += "org.apache.ignite" % "ignite-spring" % igniteVersion % "runtime"
libraryDependencies += "org.apache.ignite" % "ignite-rest-http" % igniteVersion % "runtime"