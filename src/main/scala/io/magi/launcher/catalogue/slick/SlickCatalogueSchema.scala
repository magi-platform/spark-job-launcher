package io.magi.launcher.catalogue.slick

import java.time.Instant

import io.magi.launcher.core.{Artifact, Job, JobDescriptor}

trait SlickCatalogueSchema extends SlickProvider {

    import profile.api._

    class JobDescriptors( tag : Tag ) extends Table[ JobDescriptor ]( tag, "job_descriptor" ) {

        def id = column[ Long ]( "id", O.PrimaryKey, O.AutoInc )

        def name = column[ String ]( "name", O.Unique )

        def version = column[ String ]( "version" )

        def artifactId = column[ Long ]( "artifact_id" )

        override def * = (id, name, version, artifactId).mapTo[ JobDescriptor ]
    }

    class Artifacts( tag : Tag ) extends Table[ Artifact ]( tag, "artifact" ) {

        def id = column[ Long ]( "id", O.AutoInc, O.PrimaryKey )

        def name = column[ String ]( "name", O.Unique )

        def version = column[ String ]( "version" )

        def mainClass = column[ String ]( "main_class" )

        def jarFile = column[ String ]( "jar_file" )

        override def * = (id, name, version, mainClass, jarFile).mapTo[ Artifact ]
    }

    class Jobs( tag : Tag ) extends Table[ Job ]( tag, "job" ) {

        def id = column[ Long ]( "id", O.PrimaryKey, O.AutoInc )

        def jobName = column[ String ]( "job_name", O.Unique )

        // TODO - reconcile how to enforce fk constraints w/ slick
        def descriptorId = column[ Long ]( "descriptor_id" )

        def startTime = column[ Instant ]( "start_time" )

        def endTime = column[ Option[ Instant ] ]( "end_time" )

        def appArgs = column[ String ]( "app_args" )

        override def * = (id, jobName, descriptorId, startTime, endTime, appArgs).mapTo[ Job ]
    }

}