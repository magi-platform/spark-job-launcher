package io.magi.catalogues

import java.time.Instant

import better.files.File
import io.magi.catalogue.slick.{JobCatalogue, SqliteProvider}
import io.magi.catlogue.Catalogue
import io.magi.jobs.Job
import org.scalatest.BeforeAndAfter

import scala.concurrent.Await

class SqliteJobCatalogueSuite extends SlickCatalogueTestBase[ Job ] with BeforeAndAfter {

    //@formatter:off
    before {

    }

    after {
        File( properties.getProperty( "sqlite.data.file" ) ).delete()
    }
    //@formatter:on

    override val catalogue : Catalogue[ Job ] = new JobCatalogue( properties ) with SqliteProvider

    "Job Catalogue" should "create a job" in {
        val created : Job = Await.result( catalogue.create( Job( 0, "job-1", 1, Instant.now(), None, "--env default" ) ), awaitDuration )
        val retrieved = Await.result( catalogue.getById( created.id ), awaitDuration )

        created shouldBe retrieved
    }

    "Job Catalogue" should "update an existing artifact" in {
        val created = Await.result( catalogue.create( Job( 0, "job-1", 1, Instant.now(), None, "--env default" ) ), awaitDuration )

        Await.result( catalogue.update( created.copy( appArgs = "--env prod" ) ), awaitDuration )
        val retrieved : Job = Await.result( catalogue.getById( created.id ), awaitDuration ).get
        retrieved.appArgs shouldBe "--env prod"
    }

}