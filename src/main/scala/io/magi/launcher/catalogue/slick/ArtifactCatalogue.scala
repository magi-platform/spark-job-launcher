package io.magi.launcher.catalogue.slick

import java.util.Properties

import io.magi.launcher.catlogue.Catalogue
import io.magi.launcher.core.Artifact
import org.slf4j.{Logger, LoggerFactory}

import scala.concurrent.duration.FiniteDuration
import scala.concurrent.{Await, Future}

abstract class ArtifactCatalogue( override val properties : Properties ) extends Catalogue[ Artifact ] with SlickCatalogueSchema with SlickProvider {

    private val LOG : Logger = LoggerFactory.getLogger( getClass )

    import profile.api._

    val table = TableQuery[ Artifacts ]

    override def init( ) : Unit = db.run( artifacts.schema.createIfNotExists )

    override def exists( id : Long ) : Boolean = Await.result( db.run( artifacts.filter( _.id === id ).result ), FiniteDuration( 1, "second" ) ).nonEmpty

    override def getById( id : Long ) : Future[ Option[ Artifact ] ] = db.run( artifacts.filter( _.id === id ).result.headOption )

    override def getAll( ) : Future[ Seq[ Artifact ] ] = db.run( artifacts.result )

    override def create( artifact : Artifact ) : Future[ Artifact ] = {
        db.run( table returning artifacts.map( _.id ) into ( ( artifact, generatedId ) => artifact.copy( id = generatedId ) ) += artifact )
    }

    override def update( artifact : Artifact ) : Future[ Option[ Artifact ] ] = db.run( ( table returning table ).insertOrUpdate( artifact ) )

    override def delete( artifact : Artifact ) : Future[ Int ] = db.run( artifacts.filter( _.id === artifact.id ).delete )

}