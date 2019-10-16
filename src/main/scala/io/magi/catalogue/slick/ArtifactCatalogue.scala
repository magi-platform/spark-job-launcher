package io.magi.slick

import java.util.Properties

import io.magi.artifacts.Artifact
import io.magi.catlogues.Catalogue
import io.magi.sql.slick.SlickProvider
import org.slf4j.{Logger, LoggerFactory}

import scala.concurrent.duration.FiniteDuration
import scala.concurrent.{Await, Future}

abstract class ArtifactCatalogue( override val properties : Properties ) extends SlickProvider with Catalogue[ Artifact ] {

    private val LOG : Logger = LoggerFactory.getLogger( getClass )

    import profile.api._

    val table = TableQuery[ Artifacts ]

    def init( ) : Unit = db.run( table.schema.createIfNotExists )

    def exists( id : Long ) : Boolean = {
        val existenceCheck = db.run( table.filter( _.id === id ).result )
        Await.result( existenceCheck, FiniteDuration( 1, "second" ) ).nonEmpty
    }

    def getById( id : Long ) : Future[ Option[ Artifact ] ] = db.run( table.filter( _.id === id ).result.headOption )

    def getAll( ) : Future[ Seq[ Artifact ] ] = db.run( table.result )

    def create( artifact : Artifact ) : Future[ Artifact ] = {
        db.run( table returning table.map( _.id ) into ( ( artifact, generatedId ) => artifact.copy( id = generatedId ) ) += artifact )
    }

    def update( artifact : Artifact ) : Future[ Option[ Artifact ] ] = db.run( ( table returning table ).insertOrUpdate( artifact ) )

    def delete( artifact : Artifact ) : Future[ Int ] = db.run( table.filter( _.id === artifact.id ).delete )

    protected class Artifacts( tag : Tag ) extends Table[ Artifact ]( tag, "artifact" ) {

        def id = column[ Long ]( "id", O.AutoInc, O.PrimaryKey )

        def name = column[ String ]( "name", O.Unique )

        def version = column[ String ]( "version" )

        def mainClass = column[ String ]( "main_class" )

        def jarFile = column[ String ]( "jar_file" )

        override def * = (id, name, version, mainClass, jarFile).mapTo[ Artifact ]
    }

}