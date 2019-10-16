package io.magi.catalogues

import java.util.Properties

import better.files.{File, Resource}
import io.magi.catlogues.Catalogue
import io.magi.sql.slick.SlickProvider
import org.scalatest.{BeforeAndAfter, FlatSpec, Matchers}

import scala.concurrent.duration.FiniteDuration

trait CatalogueTestBase[ T ] extends FlatSpec with Matchers with BeforeAndAfter {

    val awaitDuration = FiniteDuration( 1, "second" )

    val properties : Properties = {
        val p = new Properties()
        p.load( Resource.getAsStream( "test.conf" ) )
        p
    }

    val catalogue : SlickProvider[ T ]

    //@formatter:off
    before {
        catalogue.init()
    }

    after {
        File( properties.getProperty( "sqlite.data.file" ) ).delete()
    }
    //@formatter:on

}
