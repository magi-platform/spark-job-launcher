package io.magi.launcher.catalogue.slick

import java.util.Properties

import io.magi.launcher.catlogue.JobCatalogue
import io.magi.launcher.core.Job
import org.slf4j.{Logger, LoggerFactory}

import scala.concurrent.duration.FiniteDuration
import scala.concurrent.{Await, Future}

abstract class SlickJobCatalogue( override val properties : Properties ) extends JobCatalogue with SlickCatalogueSchema {

    private val LOG : Logger = LoggerFactory.getLogger( getClass )

    import profile.api._

    override def init( ) : Unit = db.run( jobs.schema.createIfNotExists )

    override def exists( id : Long ) : Boolean = Await.result( db.run( jobs.filter( _.id === id ).result ), FiniteDuration( 1, "second" ) ).nonEmpty

    override def getById( id : Long ) : Future[ Option[ Job ] ] = db.run( jobs.filter( _.id === id ).result.headOption )

    override def getAll( ) : Future[ Seq[ Job ] ] = db.run( jobs.result )

    override def create( job : Job ) : Future[ Job ] = {
        db.run( jobs returning jobs.map( _.id ) into ( ( job, generatedId ) => job.copy( id = generatedId ) ) += job )
    }

    override def update( job : Job ) : Future[ Option[ Job ] ] = db.run( ( jobs returning jobs ).insertOrUpdate( job ) )

    override def delete( job : Job ) : Future[ Int ] = throw new UnsupportedOperationException( "Deleting jobs is an invalid operation" )
}