package io.magi.launcher.catalogue.slick

import slick.jdbc.SQLiteProfile

import scala.util.Random

trait SqliteProvider extends SlickProvider {

    override val profile = SQLiteProfile

    import profile.api._

    lazy val dataFile = {
        if ( "_dynamic_" == properties.getProperty( "sqlite.data.file" ) ) s"/tmp/${Random.nextInt}.db"
        else properties.getProperty( "sqlite.data.file" )
    }

    lazy val jdbcUrl = s"jdbc:sqlite:${dataFile}"

    val db : profile.api.Database = Database.forURL( jdbcUrl )

}
