package io.magi.launcher.catalogue.slick

import java.util.Properties

import io.magi.launcher.catlogue.{Catalogue, JobCatalogue, JobDescriptorCatalogue}
import io.magi.launcher.core.JobDescriptor
import org.slf4j.{Logger, LoggerFactory}

import scala.concurrent.duration.FiniteDuration
import scala.concurrent.{Await, Future}

abstract class SlickJobDescriptorCatalogue( override val properties : Properties ) extends JobDescriptorCatalogue with SlickCatalogueSchema {

    private val LOG : Logger = LoggerFactory.getLogger( getClass )

    import profile.api._

    override def init( ) : Unit = db.run( jobDescriptors.schema.createIfNotExists )

    override def findByArtifact( artifactId : Long ) : Future[ Seq[ JobDescriptor ] ] = db.run( jobDescriptors.filter( _.artifactId === artifactId ).result )

    // base methods
    override def exists( id : Long ) : Boolean = Await.result( db.run( jobDescriptors.filter( _.id === id ).result ), FiniteDuration( 1, "second" ) ).nonEmpty

    override def getById( id : Long ) : Future[ Option[ JobDescriptor ] ] = db.run( jobDescriptors.filter( _.id === id ).result.headOption )

    override def getAll( ) : Future[ Seq[ JobDescriptor ] ] = db.run( jobDescriptors.result )

    override def create( descriptor : JobDescriptor ) : Future[ JobDescriptor ] = {
        db.run( jobDescriptors returning jobDescriptors.map( _.id ) into ( ( descriptor, generatedId ) => descriptor.copy( id = generatedId ) ) += descriptor )
    }

    override def update( descriptor : JobDescriptor ) : Future[ Option[ JobDescriptor ] ] = db.run( ( jobDescriptors returning jobDescriptors ).insertOrUpdate( descriptor ) )

    override def delete( descriptor : JobDescriptor ) : Future[ Int ] = db.run( jobDescriptors.filter( _.id === descriptor.id ).delete )

}