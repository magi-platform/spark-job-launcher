package io.magi.catalogues

import better.files.File
import io.magi.artifacts.Artifact
import io.magi.catalogue.slick.{ArtifactCatalogue, SqliteProvider}
import io.magi.catlogue.Catalogue
import org.scalatest.BeforeAndAfter

import scala.concurrent.Await

class SqliteArtifactSlickCatalogueSuite extends SlickCatalogueTestBase[ Artifact ] with BeforeAndAfter {

    //@formatter:off
    before {
        catalogue.init()
    }

    after {
        File( properties.getProperty( "sqlite.data.file" ) ).delete()
    }
    //@formatter:on

    override val catalogue : Catalogue[ Artifact ] = new ArtifactCatalogue( properties ) with SqliteProvider

    "Artifact Catalogue" should "save a new artifact" in {
        val created : Artifact = Await.result( catalogue.create( Artifact( 0, "1", "1", "Main", "asdf" ) ), awaitDuration )
        val retrieved : Artifact = Await.result( catalogue.getById( created.id ), awaitDuration ).get

        created shouldBe retrieved
    }

    "Artifact Catalogue" should "update an existing artifact" in {
        val created = Await.result( catalogue.create( Artifact( 0, "1", "1", "Main", "asdf" ) ), awaitDuration )

        Await.result( catalogue.update( created.copy( mainClass = "derp" ) ), awaitDuration )
        val retrieved : Artifact = Await.result( catalogue.getById( created.id ), awaitDuration ).get
        retrieved.mainClass shouldBe "derp"
    }

    "Artifact Catalogue" should "delete an artifact" in {
        val created = Await.result( catalogue.create( Artifact( 0, "1", "1", "Main", "asdf" ) ), awaitDuration )
        val deleted = Await.result( catalogue.delete( created ), awaitDuration )
        deleted shouldBe 1
    }

    "Artifact Catalogue" should "do existence checks" in {
        val created = Await.result( catalogue.create( Artifact( 0, "1", "1", "Main", "asdf" ) ), awaitDuration )
        catalogue.exists( created.id ) shouldBe true

        Await.result( catalogue.delete( created ), awaitDuration )

        catalogue.exists( created.id ) shouldBe false
    }
}
