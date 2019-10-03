package io.magi.catalouge.yaml

import io.magi.Job

case class YamlJob( id : String,
                    artifact : String,
                    params : Map[ String, String ] )

trait YamlConversions {
    implicit def domainToYaml( job : Job ) : YamlJob = YamlJob( id = job.id, artifact = job.artifact, params = job.params )

    implicit def yamlToDomain( job : YamlJob ) : Job = Job( id = job.id, artifact = job.artifact, params = job.params )
}