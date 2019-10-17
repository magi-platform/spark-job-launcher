package io.magi.launcher.catalogue.slick

import io.magi.launcher.core.Artifact

import scala.concurrent.Await

class SqliteArtifactSlickCatalogueTestSuite extends SqliteSlickTestBase {

    "Artifact Catalogue" should "save a new artifact" in {
        val created : Artifact = Await.result( artifactCatalogue.create( Artifact( 0, s"$unique()", "1", "Main", "asdf" ) ), awaitDuration )
        val retrieved : Artifact = Await.result( artifactCatalogue.getById( created.id ), awaitDuration ).get
        created shouldBe retrieved
    }

    "Artifact Catalogue" should "update an existing artifact" in {
        val created = Await.result( artifactCatalogue.create( Artifact( 0, s"$unique()", "1", "Main", "asdf" ) ), awaitDuration )
        Await.result( artifactCatalogue.update( created.copy( mainClass = "derp" ) ), awaitDuration )
        val retrieved : Artifact = Await.result( artifactCatalogue.getById( created.id ), awaitDuration ).get
        retrieved.mainClass shouldBe "derp"
    }

    "Artifact Catalogue" should "delete an artifact" in {
        val created = Await.result( artifactCatalogue.create( Artifact( 0, s"$unique()", "1", "Main", "asdf" ) ), awaitDuration )
        val deleted = Await.result( artifactCatalogue.delete( created ), awaitDuration )
        deleted shouldBe 1
    }

    "Artifact Catalogue" should "do existence checks" in {
        val created = Await.result( artifactCatalogue.create( Artifact( 0, s"$unique()", "1", "Main", "asdf" ) ), awaitDuration )
        artifactCatalogue.exists( created.id ) shouldBe true
        Await.result( artifactCatalogue.delete( created ), awaitDuration )
        artifactCatalogue.exists( created.id ) shouldBe false
    }
}