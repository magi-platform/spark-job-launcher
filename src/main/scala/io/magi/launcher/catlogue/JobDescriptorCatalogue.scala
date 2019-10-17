package io.magi.launcher.catlogue

import io.magi.launcher.core.{Job, JobDescriptor}

import scala.concurrent.Future

trait JobDescriptorCatalogue extends Catalogue[ JobDescriptor ] {
    def findByArtifact( artifactId : Long ) : Future[ Seq[ JobDescriptor ] ]
}