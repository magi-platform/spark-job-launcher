package io.magi

import org.apache.spark.launcher.SparkLauncher

trait JobRunner {
    def launchAsync( job : Job ) : Process // unit for now
}

class SparkJobRunner extends JobRunner {
    override def launchAsync( job : Job ) : Process = {
        val launcher : SparkLauncher = getSparkLauncher( job )

        launcher.launch()
    }

    private def getSparkLauncher( job : Job ) : SparkLauncher = {
        ???
    }

}