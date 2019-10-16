package io.magi.catalogues

import org.scalatest.Sequential

class CatalogueTestSuite extends Sequential( new SqliteArtifactSlickCatalogueSuite, new SqliteJobCatalogueSuite )
