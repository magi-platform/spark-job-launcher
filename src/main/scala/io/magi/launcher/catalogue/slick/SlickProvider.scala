package io.magi.launcher.catalogue.slick

import java.util.Properties

import slick.jdbc.JdbcProfile

import scala.concurrent.ExecutionContext

trait SlickProvider {

    implicit val ec = ExecutionContext.global

    lazy val properties : Properties = null
    val profile : JdbcProfile

    import profile.api._

    val db : Database

}