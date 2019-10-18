package io.magi.launcher.web

import io.magi.launcher.catlogue.{ArtifactCatalogue, JobCatalogue, JobDescriptorCatalogue}
import org.scalamock.scalatest.MockFactory
import org.scalatest.FlatSpec
import org.scalatra.test.scalatest.ScalatraSuite

class ArtifactControllerTestSuite extends FlatSpec with ScalatraSuite with MockFactory {


    val artifactCatalogue = stub[ ArtifactCatalogue ]

    val controller = new ArtifactController {
        override val jobDescriptorCatalogue : JobDescriptorCatalogue = null
        override val jobCatalogue : JobCatalogue = null
        override val artifactCatalogue : ArtifactCatalogue = artifactCatalogue
    }

    addServlet( controller, "/artifacts/*" )

    "Artifact Controller healthcheck" should "respond Ok( 200 ) when healthy" in {
        //@formatter:off
        get( "artifacts/health" ) {
            response.status shouldBe 200
        }
        //@formatter:on
    }
}