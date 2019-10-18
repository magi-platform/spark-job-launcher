package io.magi.launcher.catalogue.slick

import java.util.Properties

import better.files.Resource
import io.magi.launcher.catlogue.{ArtifactCatalogue, CatalogueContext, JobCatalogue, JobDescriptorCatalogue}
import org.scalatest.{BeforeAndAfter, FlatSpec, Matchers}

import scala.concurrent.duration.FiniteDuration
import scala.util.Random

trait SqliteSlickTestBase extends FlatSpec with CatalogueContext with Matchers with BeforeAndAfter {

    val props : Properties = {
        val p = new Properties()
        p.load( Resource.getAsStream( "test.conf" ) )
        p
    }

    override val jobDescriptorCatalogue : JobDescriptorCatalogue = new SlickJobDescriptorCatalogue with SqliteProvider {
        override lazy val properties : Properties = props
    }

    override val jobCatalogue : JobCatalogue = new SlickJobCatalogue with SqliteProvider {
        override lazy val properties : Properties = props
    }

    override val artifactCatalogue : ArtifactCatalogue = new SlickArtifactCatalogue with SqliteProvider {
        override lazy val properties : Properties = props
    }

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