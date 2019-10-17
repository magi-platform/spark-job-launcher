package io.magi.launcher.web

import io.magi.launcher.catlogue.{ArtifactCatalogue, JobCatalogue, JobDescriptorCatalogue}
import org.scalamock.scalatest.MockFactory
import org.scalatest.FlatSpec
import org.scalatra.test.scalatest.ScalatraSuite

class JobControllerTestSuite extends FlatSpec with ScalatraSuite with MockFactory {
    val apiPrefix = "magi"

    val controller = new JobController( apiPrefix ) {
        override val jobDescriptorCatalogue : JobDescriptorCatalogue = null
        override val jobCatalogue : JobCatalogue = null
        override val artifactCatalogue : ArtifactCatalogue = null
    }

    addServlet( controller, "/*" )

    "Artifact Controller healthcheck" should "respond Ok( 200 ) when healthy" in {
        get( apiPrefix + "/jobs/health" ) {
                                              response.status shouldBe 200
                                          }

    }
}
