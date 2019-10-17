package io.magi.launcher.catlogue

import io.magi.launcher.core.{Artifact, Job, JobDescriptor}

trait CatalogueContext {

    val jobDescriptorCatalogue : Catalogue[ JobDescriptor ]

    val jobCatalogue : Catalogue[ Job ]

    val artifactCatalogue : Catalogue[ Artifact ]

}
