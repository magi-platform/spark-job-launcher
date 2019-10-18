package io.magi.launcher.web

import io.magi.launcher.catlogue.CatalogueContext
import org.scalatra.{Ok, ScalatraServlet}

abstract class JobController() extends ScalatraServlet with CatalogueContext {

    //@formatter:off
    get( "/health" ) {
        Ok()
    }
    //@formatter:on

}