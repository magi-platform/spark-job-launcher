package io.magi.launcher.catalogue.slick

import java.util.Properties

import better.files.Resource
import io.magi.launcher.catlogue.{Catalogue, CatalogueContext}
import io.magi.launcher.core.{Artifact, Job, JobDescriptor}
import org.scalatest.{BeforeAndAfter, FlatSpec, Matchers}

import scala.concurrent.duration.FiniteDuration
import scala.util.Random

abstract class SqliteSlickTestBase extends FlatSpec with CatalogueContext with Matchers with BeforeAndAfter {

    val properties : Properties = {
        val p = new Properties()
        println( "Loading props" )
        p.load( Resource.getAsStream( "test.conf" ) )
        p
    }

    override val jobDescriptorCatalogue : Catalogue[ JobDescriptor ] = new JobDescriptorCatalogue( properties ) with SqliteProvider
    override val jobCatalogue : Catalogue[ Job ] = new JobCatalogue( properties ) with SqliteProvider
    override val artifactCatalogue : Catalogue[ Artifact ] = new ArtifactCatalogue( properties ) with SqliteProvider

    //@formatter:off
    before {
        artifactCatalogue.init()
        jobCatalogue.init()
        jobDescriptorCatalogue.init()
    }

    after {
        // delete db file
    }
    //@formatter:on

    def unique( ) : Int = Random.nextInt()

    val awaitDuration = FiniteDuration( 1, "second" )

}