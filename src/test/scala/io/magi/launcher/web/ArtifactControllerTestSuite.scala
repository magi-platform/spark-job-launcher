package io.magi.launcher.web

import io.magi.launcher.catlogue.{ArtifactCatalogue, JobCatalogue, JobDescriptorCatalogue}
import org.scalamock.scalatest.MockFactory
import org.scalatest.FlatSpec
import org.scalatra.test.scalatest.ScalatraSuite

class ArtifactControllerTestSuite extends FlatSpec with ScalatraSuite with MockFactory {

    val apiPrefix = "magi"

    //    val jobDescriptorCatalogue = stub[ JobDescriptorCatalogue ]
    //    val jobCatalogue = stub[ JobCatalogue ]
    val artifactCatalogue = stub[ ArtifactCatalogue ]

    val controller = new ArtifactController( apiPrefix ) {
        override val jobDescriptorCatalogue : JobDescriptorCatalogue = null
        override val jobCatalogue : JobCatalogue = null
        override val artifactCatalogue : ArtifactCatalogue = artifactCatalogue
    }

    addServlet( controller, "/*" )

    "Artifact Controller healthcheck" should "respond Ok( 200 ) when healthy" in {
        get( apiPrefix + "/artifacts/health" ) {
                                                   response.status shouldBe 200
                                               }

    }
}