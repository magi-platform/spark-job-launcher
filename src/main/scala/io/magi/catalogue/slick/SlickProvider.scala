package io.magi.sql.slick

import java.util.Properties

import slick.jdbc.JdbcProfile

import scala.concurrent.ExecutionContext

trait SlickProvider {

    implicit val ec = ExecutionContext.global

    val properties : Properties
    val profile : JdbcProfile

    import profile.api._

    val db : Database

}