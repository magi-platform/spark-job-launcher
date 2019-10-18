package io.magi.launcher.api

import io.magi.launcher.core.{Artifact, JobDescriptor}

case class LaunchJob( jobDescriptor : JobDescriptor,
                      artifact : Artifact,
                      initiator : String,
                      jobArgs : String )
