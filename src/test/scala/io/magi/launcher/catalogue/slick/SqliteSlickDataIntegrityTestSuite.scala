package io.magi.launcher.catalogue.slick

import io.magi.launcher.core.{Artifact, JobDescriptor}

import scala.concurrent.Await

class SqliteSlickDataIntegrityTestSuite extends SqliteSlickTestBase {

    "Artifact deletion" should "cascade and remove any dependent Job Descriptors" in {
        val artifact = Await.result( artifactCatalogue.create( Artifact( 0, s"$unique()", "1", "Main", "asdf" ) ), awaitDuration )
        val descriptor = Await.result( jobDescriptorCatalogue.create( JobDescriptor( 0, s"${unique()}", "1.0.0", artifact.id ) ), awaitDuration )

        val deletion = Await.result( artifactCatalogue.delete( artifact ), awaitDuration )

        deletion shouldBe 1

        val result = Await.result( jobDescriptorCatalogue.getById( descriptor.id ), awaitDuration )

        println( result )
    }

}