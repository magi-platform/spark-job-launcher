import sbt._
import Dependencies._

organization in ThisBuild := "io.magi"
name := "spark-job-launcher"
version in ThisBuild := "0.0.1-SNAPSHOT"
scalaVersion in ThisBuild := "2.12.7"

resolvers in ThisBuild ++= Seq( "Sonatype releases" at "https://oss.sonatype.org/content/repositories/releases",
                                "Spray IO Repository" at "http://repo.spray.io/",
                                "Maven Central" at "https://repo1.maven.org/maven2/",
                                "Typesafe repository" at "http://repo.typesafe.com/typesafe/releases/",
                                "JCenter" at "http://jcenter.bintray.com",
                                "Local Ivy Repository" at s"file://${System.getProperty( "user.home" )}/.ivy2/local/default" )

lazy val root = ( project in file( "." ) ).settings( libraryDependencies ++= spark
                                                                             ++ slick
                                                                             ++ sqlite
                                                                             ++ scalatra
                                                                             ++ jackson
                                                                             ++ logging
                                                                             ++ betterFiles
                                                                             ++ scalaTest
                                                                             ++ scalaMock )

enablePlugins( JavaAppPackaging )

test in assembly := {}

assemblyMergeStrategy in assembly := {
    case PathList( "META-INF", "MANIFEST.MF" ) => MergeStrategy.discard
    case PathList( "reference.conf" ) => MergeStrategy.concat
    case x => MergeStrategy.last
}