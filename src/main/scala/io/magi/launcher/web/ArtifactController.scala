package io.magi.launcher.web

import io.magi.launcher.catlogue.CatalogueContext
import org.scalatra.{Ok, ScalatraServlet}

abstract class ArtifactController( apiPrefix : String ) extends ScalatraServlet with CatalogueContext {

    val urlContext : String = s"/${apiPrefix}/artifacts"

    get( s"${urlContext}/health" ) {
                                       Ok()
                                   }

}