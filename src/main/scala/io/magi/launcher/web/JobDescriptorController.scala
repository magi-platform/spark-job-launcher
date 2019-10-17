package io.magi.launcher.web

import io.magi.launcher.catlogue.CatalogueContext
import org.scalatra.{Ok, ScalatraServlet}

abstract class JobDescriptorController( apiPrefix : String ) extends ScalatraServlet with CatalogueContext {

    val urlContext : String = s"/${apiPrefix}/job-descriptors"

    get( s"${urlContext}/health" ) {
                                       Ok()
                                   }

}