package io.magi.launcher.catalogue.slick

import io.magi.launcher.core.{Artifact, JobDescriptor}

import scala.concurrent.Await
import scala.util.Random

class SqliteJobDescriptorCatalogueTestSuite extends SqliteSlickTestBase {

    "Job Descriptor Catalogue" should "create a new Job Descriptor" in {
        val artifact : Artifact = Await.result( artifactCatalogue.create( Artifact( 0, s"$unique()", "1", "Main", "asdf" ) ), awaitDuration )
        val created : JobDescriptor = Await.result( jobDescriptorCatalogue.create( JobDescriptor( 0, s"${unique()}", "1.0.0", artifact.id ) ), awaitDuration )
        val retrieved = Await.result( jobDescriptorCatalogue.getById( created.id ), awaitDuration ).get

        created shouldBe retrieved
    }

    "Job Descriptor Catalogue" should "update an existing Job Descriptor" in {
        val artifact : Artifact = Await.result( artifactCatalogue.create( Artifact( 0, s"$unique()", "1", "Main", "asdf" ) ), awaitDuration )
        val created = Await.result( jobDescriptorCatalogue.create( JobDescriptor( 0, s"${unique()}", "1.0.0", artifact.id ) ), awaitDuration )
        Await.result( jobDescriptorCatalogue.update( created.copy( version = "2.0.0" ) ), awaitDuration )
        val retrieved : JobDescriptor = Await.result( jobDescriptorCatalogue.getById( created.id ), awaitDuration ).get
        retrieved.version shouldBe "2.0.0"
    }

    //@formatter:off
    "Job Descriptor Catalogue" should "not able to delete a Job Descriptor" in {
        //TODO - implement cascading deletion behavior
    }
    //@formatter:on

    "Job Descriptor Catalogue" should "do existence checks" in {
        val artifact : Artifact = Await.result( artifactCatalogue.create( Artifact( 0, s"$unique()", "1", "Main", "asdf" ) ), awaitDuration )
        val created = Await.result( jobDescriptorCatalogue.create( JobDescriptor( 0, s"${unique()}", "1.0.0", artifact.id ) ), awaitDuration )
        jobDescriptorCatalogue.exists( created.id ) shouldBe true
        jobDescriptorCatalogue.exists( Random.nextInt ) shouldBe false
    }

}