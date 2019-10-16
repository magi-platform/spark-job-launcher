package io.magi.catlogue

import scala.concurrent.Future

trait Catalogue[ T ] {

    def init( ) : Unit

    def exists( id : Long ) : Boolean

    def getById( id : Long ) : Future[ Option[ T ] ]

    def getAll( ) : Future[ Seq[ T ] ]

    def create( artifact : T ) : Future[ T ]

    def update( artifact : T ) : Future[ Option[ T ] ]

    def delete( artifact : T ) : Future[ Int ]

}
