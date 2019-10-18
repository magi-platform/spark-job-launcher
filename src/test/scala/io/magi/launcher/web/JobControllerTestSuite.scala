package io.magi.launcher.web

import io.magi.launcher.catlogue.{ArtifactCatalogue, JobCatalogue, JobDescriptorCatalogue}
import org.scalamock.scalatest.MockFactory
import org.scalatest.FlatSpec
import org.scalatra.test.scalatest.ScalatraSuite

class JobControllerTestSuite extends FlatSpec with ScalatraSuite with MockFactory {

    val controller = new JobController {
        override val jobDescriptorCatalogue : JobDescriptorCatalogue = null
        override val jobCatalogue : JobCatalogue = null
        override val artifactCatalogue : ArtifactCatalogue = null
    }

    addServlet( controller, "/jobs/*" )

    "Artifact Controller healthcheck" should "respond Ok( 200 ) when healthy" in {
        //@formatter:off
        get( "/jobs/health" ) {
            response.status shouldBe 200
        }
        //@formatter:on
    }
}
