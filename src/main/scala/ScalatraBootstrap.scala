import java.util.Properties

import io.magi.launcher.catalogue.slick.{SlickArtifactCatalogue, SlickJobCatalogue, SlickJobDescriptorCatalogue, SqliteProvider}
import io.magi.launcher.catlogue.{ArtifactCatalogue, CatalogueContext, JobCatalogue, JobDescriptorCatalogue}
import io.magi.launcher.web.{ArtifactController, JobController, JobDescriptorController}
import javax.servlet.ServletContext
import org.scalatra.LifeCycle

class ScalatraBootstrap extends LifeCycle {

    override def init( context : ServletContext ) : Unit = {
        val props : Properties = new Properties()
        props.setProperty( "sqlite.data.file", "/tmp/data.db" )

        val descriptorCatalogue : JobDescriptorCatalogue = new SlickJobDescriptorCatalogue with SqliteProvider {
            override lazy val properties : Properties = props
        }
        val jobsCatalogue : JobCatalogue = new SlickJobCatalogue with SqliteProvider {
            override lazy val properties : Properties = props
        }
        val artifactsCatalogue : ArtifactCatalogue = new SlickArtifactCatalogue with SqliteProvider {
            override lazy val properties : Properties = props
        }

        artifactsCatalogue.init()
        descriptorCatalogue.init()
        jobsCatalogue.init()

        val artifactController = new ArtifactController() with CatalogueContext with SqliteProvider {
            override lazy val properties : Properties = props
            override val jobDescriptorCatalogue : JobDescriptorCatalogue = descriptorCatalogue
            override val jobCatalogue : JobCatalogue = jobsCatalogue
            override val artifactCatalogue : ArtifactCatalogue = artifactsCatalogue
        }

        val jobController = new JobController() with CatalogueContext with SqliteProvider {
            override lazy val properties : Properties = props
            override val jobDescriptorCatalogue : JobDescriptorCatalogue = descriptorCatalogue
            override val jobCatalogue : JobCatalogue = jobsCatalogue
            override val artifactCatalogue : ArtifactCatalogue = artifactsCatalogue
        }

        val descriptorController = new JobDescriptorController() with CatalogueContext with SqliteProvider {
            override lazy val properties : Properties = props
            override val jobDescriptorCatalogue : JobDescriptorCatalogue = descriptorCatalogue
            override val jobCatalogue : JobCatalogue = jobsCatalogue
            override val artifactCatalogue : ArtifactCatalogue = artifactsCatalogue
        }

        context.mount( artifactController, "/artifacts/*" )
        context.mount( jobController, "/jobs/*" )
        context.mount( descriptorController, "/job-descriptors/*" )
    }

}