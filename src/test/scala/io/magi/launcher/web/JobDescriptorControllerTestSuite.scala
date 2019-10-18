package io.magi.launcher.web

import io.magi.launcher.catlogue.{ArtifactCatalogue, JobCatalogue, JobDescriptorCatalogue}
import org.scalamock.scalatest.MockFactory
import org.scalatest.FlatSpec
import org.scalatra.test.scalatest.ScalatraSuite

class JobDescriptorControllerTestSuite extends FlatSpec with ScalatraSuite with MockFactory {


    val jobDescriptorCatalogue = stub[ JobDescriptorCatalogue ]
    val artifactCatalogue = stub[ ArtifactCatalogue ]

    val controller = new JobDescriptorController {
        override val jobDescriptorCatalogue : JobDescriptorCatalogue = null
        override val jobCatalogue : JobCatalogue = null
        override val artifactCatalogue : ArtifactCatalogue = null
    }

    addServlet( controller, "/job-descriptors/*" )

    //@formatter:off
    "Artifact Controller healthcheck" should "respond Ok( 200 ) when healthy" in {
        get( "/job-descriptors/health" ) {
            response.status shouldBe 200
        }
        //@formatter:on
    }

}