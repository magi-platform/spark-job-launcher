package io.magi.catalogue.slick

import java.time.Instant
import java.util.Properties

import io.magi.catlogue.Catalogue
import io.magi.jobs.Job
import org.slf4j.{Logger, LoggerFactory}

import scala.concurrent.duration.FiniteDuration
import scala.concurrent.{Await, Future}

abstract class JobCatalogue( override val properties : Properties ) extends SlickProvider with Catalogue[ Job ] {

    private val LOG : Logger = LoggerFactory.getLogger( getClass )

    import profile.api._

    val table = TableQuery[ Jobs ]

    override def schema( ) : profile.SchemaDescription = table.schema

    override def init( ) : Unit = db.run( table.schema.createIfNotExists )

    override def exists( id : Long ) : Boolean = Await.result( db.run( table.filter( _.id === id ).result ), FiniteDuration( 1, "second" ) ).nonEmpty

    override def getById( id : Long ) : Future[ Option[ Job ] ] = db.run( table.filter( _.id === id ).result.headOption )

    override def getAll( ) : Future[ Seq[ Job ] ] = db.run( table.result )

    override def create( job : Job ) : Future[ Job ] = {
        db.run( table returning table.map( _.id ) into ( ( job, generatedId ) => job.copy( id = generatedId ) ) += job )
    }

    override def update( job : Job ) : Future[ Option[ Job ] ] = db.run( ( table returning table ).insertOrUpdate( job ) )

    override def delete( job : Job ) : Future[ Int ] = throw new UnsupportedOperationException( "Deleting jobs is an invalid operation" )

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
