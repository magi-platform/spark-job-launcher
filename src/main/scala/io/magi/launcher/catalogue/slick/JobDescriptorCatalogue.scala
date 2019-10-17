package io.magi.launcher.catalogue.slick

import java.util.Properties

import io.magi.launcher.catlogue.Catalogue
import io.magi.launcher.core.JobDescriptor
import org.slf4j.{Logger, LoggerFactory}

import scala.concurrent.duration.FiniteDuration
import scala.concurrent.{Await, Future}

abstract class JobDescriptorCatalogue( override val properties : Properties ) extends SlickCatalogueSchema with Catalogue[ JobDescriptor ] {

    private val LOG : Logger = LoggerFactory.getLogger( getClass )

    import profile.api._

    val table = TableQuery[ JobDescriptors ]

    override def init( ) : Unit = db.run( table.schema.createIfNotExists )

    override def exists( id : Long ) : Boolean = Await.result( db.run( table.filter( _.id === id ).result ), FiniteDuration( 1, "second" ) ).nonEmpty

    override def getById( id : Long ) : Future[ Option[ JobDescriptor ] ] = ???

    override def getAll( ) : Future[ Seq[ JobDescriptor ] ] = ???

    override def create( artifact : JobDescriptor ) : Future[ JobDescriptor ] = ???

    override def update( artifact : JobDescriptor ) : Future[ Option[ JobDescriptor ] ] = ???

    override def delete( artifact : JobDescriptor ) : Future[ Int ] = ???

}