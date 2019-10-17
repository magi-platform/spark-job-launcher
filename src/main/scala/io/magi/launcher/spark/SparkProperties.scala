package io.magi.launcher.spark

import java.util.Properties


object SparkProperties {

    def apply( properties : Properties ) : SparkProperties = {

        var sparkProps = SparkProperties()
        if ( properties.getProperty( "spark.home" ) != null ) sparkProps = sparkProps.copy( sparkHome = properties.getProperty( "spark.home" ) )
        if ( properties.getProperty( "spark.jar.apps.dir" ) != null ) sparkProps = sparkProps.copy( sparkHome = properties.getProperty( "spark.jar.apps.dir" ) )
        if ( properties.getProperty( "spark.master.url" ) != null ) sparkProps = sparkProps.copy( sparkHome = properties.getProperty( "spark.master.url" ) )
        if ( properties.getProperty( "spark.master.port" ) != null ) sparkProps = sparkProps.copy( sparkHome = properties.getProperty( "spark.master.port" ) )
        if ( properties.getProperty( "spark.deploy.mode" ) != null ) sparkProps = sparkProps.copy( sparkHome = properties.getProperty( "spark.deploy.mode" ) )

        sparkProps
    }

}

case class SparkProperties( sparkHome : String = "/opt/spark",
                            sparkJarAppsDir : String = "/opt/app/data",
                            sparkMasterHost : String = "localhost",
                            sparkMasterPort : Int = 7077,
                            deployMode : String = "client" ) {

    val masterUrl = s"spark://${sparkMasterHost}:${sparkMasterPort}"

}