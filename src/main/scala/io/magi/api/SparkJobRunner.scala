package io.magi.api

import java.time.Instant

import io.magi.jobs.Job
import io.magi.spark.SparkProperties
import org.apache.spark.launcher.{SparkAppHandle, SparkLauncher}
import org.slf4j.{Logger, LoggerFactory}

import scala.util.{Failure, Success, Try}

class SparkJobRunner( sparkProps : SparkProperties ) {

    private val LOG : Logger = LoggerFactory.getLogger( getClass )

    def launchJob( command : LaunchJob ) : Try[ (SparkAppHandle, Job) ] = {
        try {
            val startTime = Instant.now()
            val jobName = s"${command.jobDescriptor.name}-${System.currentTimeMillis()}"
            val handler = new SparkLauncher()
              .setAppName( command.jobDescriptor.name )
              .setSparkHome( sparkProps.sparkHome )
              .setMaster( sparkProps.masterUrl )
              .setDeployMode( sparkProps.deployMode )
              .addJar( s"${sparkProps.sparkJarAppsDir}/${command.artifact.jarFile}" )
              .setMainClass( command.artifact.mainClass )
              .addAppArgs( command.jobArgs.mkString( " " ) )
              .startApplication()
            Success( (handler, Job( 0, jobName, startTime, None, command.jobArgs )) )
        }
        catch {
            case t : Throwable => {
                LOG.error( s"Unable to start spark job for ${command.jobDescriptor.name}" )
                LOG.error( s"Caught throwable error/exception : ${t.getClass.getName} : ${t.getMessage} : ${t.getCause}" )
                t.printStackTrace()
                Failure( t )
            }
        }
    }
}