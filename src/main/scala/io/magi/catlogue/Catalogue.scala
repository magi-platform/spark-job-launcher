package io.magi.catlogues

import io.magi.artifacts.Artifact

import scala.concurrent.Future

trait Catalogue[ T ] {

    def init( ) : Unit

    def exists( id : Long ) : Boolean

    def getById( id : Long ) : Future[ Option[ T ] ]

    def getAll( ) : Future[ Seq[ T ] ]

    def create( artifact : Artifact ) : Future[ T ]

    def update( artifact : Artifact ) : Future[ Option[ T ] ]

    def delete( artifact : Artifact ) : Future[ Int ]

}
