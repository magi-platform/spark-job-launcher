logLevel := Level.Warn

resolvers += "Typesafe repository" at "http://repo.typesafe.com/typesafe/releases/"

addSbtPlugin( "com.eed3si9n" % "sbt-assembly" % "0.14.6" )
addSbtPlugin( "com.typesafe.sbt" % "sbt-native-packager" % "1.3.2" )

// required for using scalatra
// addSbtPlugin( "com.typesafe.sbt" % "sbt-twirl" % "1.3.13" )
// addSbtPlugin( "org.scalatra.sbt" % "sbt-scalatra" % "1.0.2" )