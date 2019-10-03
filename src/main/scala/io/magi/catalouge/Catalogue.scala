package io.magi.catalouge

import io.magi.Job

import scala.util.Try

trait Catalogue {
    def exists( id : String ) : Boolean

    def getById( id : String ) : Option[ Job ]

    def create( job : Job ) : Try[ Job ]

    def update( job : Job ) : Try[ Job ]
}