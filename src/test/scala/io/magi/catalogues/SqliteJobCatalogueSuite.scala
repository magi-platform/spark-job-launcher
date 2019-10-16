package io.magi.catalogues

import java.time.Instant

import io.magi.catalogue.slick.{JobCatalogue, SqliteProvider}
import io.magi.catlogue.Catalogue
import io.magi.jobs.Job

import scala.concurrent.Await
import scala.util.Random

class SqliteJobCatalogueSuite extends SqliteSlickTestBase[ Job ] {

    override val catalogue : Catalogue[ Job ] = new JobCatalogue( properties ) with SqliteProvider

    "Job Catalogue" should "create a new Job" in {
        val created : Job = Await.result( catalogue.create( Job( 0, "job-1", 1, Instant.now(), None, "--env default" ) ), awaitDuration )
        val retrieved = Await.result( catalogue.getById( created.id ), awaitDuration ).get

        created shouldBe retrieved
    }

    "Job Catalogue" should "update an existing Job" in {
        val created = Await.result( catalogue.create( Job( 0, "job-1", 1, Instant.now(), None, "--env default" ) ), awaitDuration )
        Await.result( catalogue.update( created.copy( appArgs = "--env prod" ) ), awaitDuration )
        val retrieved : Job = Await.result( catalogue.getById( created.id ), awaitDuration ).get
        retrieved.appArgs shouldBe "--env prod"
    }

    //@formatter:off
    "Job Catalogue" should "not able to delete a Job" in {
        intercept[ UnsupportedOperationException ] {
            val created = Await.result( catalogue.create( Job( 0, "job-1", 1, Instant.now(), None, "--env default" ) ), awaitDuration )
            Await.result( catalogue.update( created.copy( appArgs = "--env prod" ) ), awaitDuration )
            Await.result( catalogue.delete( created ), awaitDuration )
        }
    }
    //@formatter:on

    "Job Catalogue" should "do existence checks" in {
        val created = Await.result( catalogue.create( Job( 0, "job-1", 1, Instant.now(), None, "--env default" ) ), awaitDuration )
        catalogue.exists( created.id ) shouldBe true
        catalogue.exists( Random.nextInt ) shouldBe false
    }

}