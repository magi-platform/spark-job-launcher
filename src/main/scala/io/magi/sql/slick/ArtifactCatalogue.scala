package io.magi.slick

import java.util.Properties

import io.magi.artifacts.Artifact
import io.magi.sql.slick.SlickComponent
import org.slf4j.{Logger, LoggerFactory}

import scala.concurrent.Future
import scala.util.{Failure, Success}

abstract class ArtifactCatalogue( override val properties : Properties ) {
    this : SlickComponent =>

    private val LOG : Logger = LoggerFactory.getLogger( getClass )

    import profile.api._

    val table = TableQuery[ Artifacts ]

    def init( ) : Unit = db.run( table.schema.createIfNotExists )


    def getById( id : Long ) : Future[ Option[ Artifact ] ] = db.run( table.filter( _.id === id ).result.headOption )

    def getAll( ) : Future[ Seq[ Artifact ] ] = db.run( table.result )

    def create( artifact : Artifact ) : Future[ Artifact ] = {
        db.run( table returning table.map( _.id ) into ( ( artifact, generatedId ) => artifact.copy( id = generatedId ) ) += artifact )
    }

    protected class Artifacts( tag : Tag ) extends Table[ Artifact ]( tag, "artifact" ) {

        def id = column[ Long ]( "id", O.AutoInc, O.PrimaryKey )

        def name = column[ String ]( "name", O.Unique )

        def version = column[ String ]( "version" )

        def mainClass = column[ String ]( "main_class" )

        def jarFile = column[ String ]( "jar_file" )

        override def * = (id, name, version, mainClass, jarFile).mapTo[ Artifact ]
    }

}