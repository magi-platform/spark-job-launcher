package io.magi.catalouge.yaml

import io.magi.Job
import io.magi.catalouge.Catalogue

import scala.util.Try

class YamlJobCatalogue extends Catalogue {
    override def exists( id : String ) : Boolean = ???

    override def getById( id : String ) : Option[ Job ] = ???

    override def create( job : Job ) : Try[ Job ] = ???

    override def update( job : Job ) : Try[ Job ] = ???
}