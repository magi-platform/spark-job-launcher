package io.magi.catalogues

import java.util.Properties

import better.files.{File, Resource}
import io.magi.artifacts.Artifact
import io.magi.slick.ArtifactCatalogue
import io.magi.sql.slick.SqliteProvider
import org.scalatest.{BeforeAndAfterAll, FlatSpec, Matchers}

import scala.collection.JavaConverters._
import scala.concurrent.Await
import scala.concurrent.duration.FiniteDuration

class SqliteArtifactCatalogueTestSuite extends FlatSpec with Matchers with BeforeAndAfterAll {

    val awaitDuration = FiniteDuration( 1, "second" )

    val properties : Properties = {
        val p = new Properties()
        p.load( Resource.getAsStream( "test.conf" ) )
        p
    }

    override protected def beforeAll( ) : Unit = catalogue.init()

    override protected def afterAll( ) : Unit = File( properties.getProperty( "sqlite.data.file" ) ).delete()

    val catalogue : ArtifactCatalogue = new ArtifactCatalogue( properties ) with SqliteProvider

    "Artifact Catalogue" should "save a new artifact" in {
        properties.asScala.foreach( println )
        val original = Artifact( 0, "1", "1", "Main", "asdf" )
        val inserted = Await.result( catalogue.create( original ), awaitDuration )
        val retreived = Await.result( catalogue.getById( inserted.id ), awaitDuration )
        inserted shouldBe retreived.get
    }
}
