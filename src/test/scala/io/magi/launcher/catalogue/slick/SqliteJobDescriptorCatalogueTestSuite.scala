package io.magi.launcher.catalogue.slick

import io.magi.launcher.core.{Artifact, JobDescriptor}

import scala.concurrent.Await
import scala.util.Random

class SqliteJobDescriptorCatalogueTestSuite extends SqliteSlickTestBase {

    "Job Descriptor Catalogue" should "create a new Job Descriptor" in {
        val created : JobDescriptor = Await.result( jobDescriptorCatalogue.create( JobDescriptor( 0, s"${unique()}", "1.0.0", 1 ) ), awaitDuration )
        val retrieved = Await.result( jobDescriptorCatalogue.getById( created.id ), awaitDuration ).get

        created shouldBe retrieved
    }

    "Job Descriptor Catalogue" should "update an existing Job Descriptor" in {
        val created = Await.result( jobDescriptorCatalogue.create( JobDescriptor( 0, s"${unique()}", "1.0.0", 1 ) ), awaitDuration )
        Await.result( jobDescriptorCatalogue.update( created.copy( version = "2.0.0" ) ), awaitDuration )
        val retrieved : JobDescriptor = Await.result( jobDescriptorCatalogue.getById( created.id ), awaitDuration ).get
        retrieved.version shouldBe "2.0.0"
    }

    "Job Descriptor Catalogue" should "find by artfiact" in {
        val created1 = Await.result( jobDescriptorCatalogue.create( JobDescriptor( 0, s"${unique()}", "1.0.0", 2 ) ), awaitDuration )
        val created2 = Await.result( jobDescriptorCatalogue.create( JobDescriptor( 0, s"${unique()}", "1.0.0", 2 ) ), awaitDuration )

        val found = Await.result( jobDescriptorCatalogue.findByArtifact( 2 ), awaitDuration )

        found.size shouldBe 2

        found.contains( created1 ) shouldBe true
        found.contains( created2 ) shouldBe true
    }

    "Job Descriptor Catalogue" should "do existence checks" in {
        val created = Await.result( jobDescriptorCatalogue.create( JobDescriptor( 0, s"${unique()}", "1.0.0", 1 ) ), awaitDuration )
        jobDescriptorCatalogue.exists( created.id ) shouldBe true
        jobDescriptorCatalogue.exists( Random.nextInt ) shouldBe false
    }

}