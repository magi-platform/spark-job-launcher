package io.magi.catalogues

import java.util.Properties

import better.files.{File, Resource}
import io.magi.catalogue.slick.{SlickProvider, SqliteProvider}
import io.magi.catlogue.Catalogue
import org.scalatest.{BeforeAndAfter, FlatSpec, Matchers}

import scala.concurrent.duration.FiniteDuration
import scala.util.Random

trait SqliteSlickTestBase[ T ] extends FlatSpec with Matchers with BeforeAndAfter {

    //@formatter:off
    before {
        catalogue.init()
    }

    after {
        File( catalogue.asInstanceOf[ SqliteProvider ].dataFile ).delete()
    }
    //@formatter:off


    def unique( ) : Int = Random.nextInt()

    val awaitDuration = FiniteDuration( 1, "second" )

    val properties : Properties = {
        val p = new Properties()
        p.load( Resource.getAsStream( "test.conf" ) )
        p
    }

    val catalogue : Catalogue[ T ]

}