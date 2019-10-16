package io.magi.jobs

import java.time.Instant

case class Job( id : Long,
                jobName : String,
                startTime : Instant,
                endTime : Option[ Instant ],
                appArgs : String )