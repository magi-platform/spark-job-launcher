package io.magi.sql.slick

import slick.jdbc.SQLiteProfile

trait SqliteProvider extends SlickComponent {

    override val profile = SQLiteProfile

    import profile.api._

    lazy val jdbcUrl = {
        s"jdbc:sqlite:/${properties.getProperty( "sqlite.data.file" )}"
    }

    val db : profile.api.Database = Database.forURL( jdbcUrl )

}
