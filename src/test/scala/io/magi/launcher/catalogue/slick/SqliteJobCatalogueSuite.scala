package io.magi.launcher.catalogue.slick

import java.time.Instant

import io.magi.launcher.core.Job

import scala.concurrent.Await
import scala.util.Random

class SqliteJobCatalogueSuite extends SqliteSlickTestBase {

    "Job Catalogue" should "create a new Job" in {
        val created : Job = Await.result( jobCatalogue.create( Job( 0, s"${unique()}", 1, Instant.now(), None, "--env default" ) ), awaitDuration )
        val retrieved = Await.result( jobCatalogue.getById( created.id ), awaitDuration ).get

        created shouldBe retrieved
    }

    "Job Catalogue" should "update an existing Job" in {
        val created = Await.result( jobCatalogue.create( Job( 0, s"${unique()}", 1, Instant.now(), None, "--env default" ) ), awaitDuration )
        Await.result( jobCatalogue.update( created.copy( appArgs = "--env prod" ) ), awaitDuration )
        val retrieved : Job = Await.result( jobCatalogue.getById( created.id ), awaitDuration ).get
        retrieved.appArgs shouldBe "--env prod"
    }

    //@formatter:off
    "Job Catalogue" should "not able to delete a Job" in {
        intercept[ UnsupportedOperationException ] {
            val created = Await.result( jobCatalogue.create( Job( 0, s"${unique()}", 1, Instant.now(), None, "--env default" ) ), awaitDuration )
            Await.result( jobCatalogue.update( created.copy( appArgs = "--env prod" ) ), awaitDuration )
            Await.result( jobCatalogue.delete( created ), awaitDuration )
        }
    }
    //@formatter:on

    "Job Catalogue" should "do existence checks" in {
        val created = Await.result( jobCatalogue.create( Job( 0, s"${unique()}", 1, Instant.now(), None, "--env default" ) ), awaitDuration )
        jobCatalogue.exists( created.id ) shouldBe true
        jobCatalogue.exists( Random.nextInt ) shouldBe false
    }

}