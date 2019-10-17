import sbt._

object Dependencies {

    val slf4jVersion = "1.7.20"
    val logbackVersion = "1.2.3"
    val scalaTestVersion = "3.0.5"
    val betterFilesVersion = "3.8.0"
    val jacksonVersion = "2.10.0"
    val sparkVersion = "2.4.4"
    val slickVersion = "3.3.1"
    val sqliteJdbcVersion = "3.28.0"
    val scalatraVersion = "2.6.5"
    val jettyWebappVersion = "9.4.21.v20190926"
    val servletApiVersion = "3.1.0"
    val scalaMockVersion = "4.4.0"

    val logging = Seq( "org.slf4j" % "slf4j-api" % slf4jVersion,
                       "ch.qos.logback" % "logback-classic" % logbackVersion )

    val betterFiles = Seq( "com.github.pathikrit" %% "better-files" % betterFilesVersion % Test )

    val jackson = Seq( "com.fasterxml.jackson.core" % "jackson-core" % jacksonVersion,
                       "com.fasterxml.jackson.core" % "jackson-annotations" % jacksonVersion,
                       "com.fasterxml.jackson.core" % "jackson-databind" % jacksonVersion,
                       "com.fasterxml.jackson.dataformat" % "jackson-dataformat-yaml" % jacksonVersion )

    val scalatra = Seq( "org.scalatra" %% "scalatra" % scalatraVersion,
                        "org.scalatra" %% "scalatra-scalatest" % scalatraVersion % Test,
                        "org.eclipse.jetty" % "jetty-webapp" % jettyWebappVersion,
                        "javax.servlet" % "javax.servlet-api" % servletApiVersion )


    val slick = Seq( "com.typesafe.slick" %% "slick-hikaricp" % slickVersion,
                     "com.typesafe.slick" %% "slick-hikaricp" % slickVersion )

    val sqlite = Seq( "org.xerial" % "sqlite-jdbc" % sqliteJdbcVersion )

    val spark = Seq( "org.apache.spark" %% "spark-launcher" % sparkVersion )

    val scalaTest = Seq( "org.scalatest" %% "scalatest" % scalaTestVersion % Test )

    val scalaMock = Seq( "org.scalamock" %% "scalamock" % scalaMockVersion )

}