package io.magi.launcher.web

import io.magi.launcher.catlogue.{ArtifactCatalogue, JobCatalogue, JobDescriptorCatalogue}
import org.scalamock.scalatest.MockFactory
import org.scalatest.FlatSpec
import org.scalatra.test.scalatest.ScalatraSuite

class JobDescriptorControllerTestSuite extends FlatSpec with ScalatraSuite with MockFactory {

    val apiPrefix = "magi"

    val jobDescriptorCatalogue = stub[ JobDescriptorCatalogue ]
    val artifactCatalogue = stub[ ArtifactCatalogue ]

    val controller = new JobDescriptorController( apiPrefix ) {
        override val jobDescriptorCatalogue : JobDescriptorCatalogue = null
        override val jobCatalogue : JobCatalogue = null
        override val artifactCatalogue : ArtifactCatalogue = null
    }

    addServlet( controller, "/*" )

    "Artifact Controller healthcheck" should "respond Ok( 200 ) when healthy" in {
        get( apiPrefix + "/job-descriptors/health" ) {
                                                   response.status shouldBe 200
                                               }

    }

}
