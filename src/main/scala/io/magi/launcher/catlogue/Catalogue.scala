package io.magi.launcher.catlogue

import scala.concurrent.Future

trait Catalogue[ T ] {

    def init( ) : Unit

    def exists( id : Long ) : Boolean

    def getById( id : Long ) : Future[ Option[ T ] ]

    def getAll( ) : Future[ Seq[ T ] ]

    def create( item : T ) : Future[ T ]

    def update( item : T ) : Future[ Option[ T ] ]

    def delete( item : T ) : Future[ Int ]

}
