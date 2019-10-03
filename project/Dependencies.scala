import sbt.Keys._
import sbt._

object Dependencies {

    val slf4jVersion = "1.7.20"
    val logbackVersion = "1.2.3"
    val scalaTestVersion = "3.0.5"
    val betterFilesVersion = "3.8.0"
    val jacksonVersion = "2.10.0"
    val sparkVersion = "2.4.4"

    val logging = Seq( "org.slf4j" % "slf4j-api" % slf4jVersion,
                       "ch.qos.logback" % "logback-classic" % logbackVersion )

    val betterFiles = Seq( "com.github.pathikrit" %% "better-files" % betterFilesVersion )

    val scalaTest = Seq( "org.scalatest" %% "scalatest" % scalaTestVersion % "test" )

    val jackson = Seq( "com.fasterxml.jackson.core" % "jackson-core" % jacksonVersion,
                       "com.fasterxml.jackson.core" % "jackson-annotations" % jacksonVersion,
                       "com.fasterxml.jackson.core" % "jackson-databind" % jacksonVersion,
                       "com.fasterxml.jackson.dataformat" % "jackson-dataformat-yaml" % jacksonVersion )

    val spark = Seq( "org.apache.spark" %% "spark-launcher" % sparkVersion )

}
