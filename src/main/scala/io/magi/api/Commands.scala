package io.magi.api

import io.magi.artifacts.Artifact
import io.magi.jobs.JobDescriptor

case class LaunchJob( jobDescriptor : JobDescriptor,
                      artifact : Artifact,
                      initiator : String,
                      jobArgs : String )
