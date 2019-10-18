package io.magi.launcher.core

import java.time.Instant

case class Job( id : Long,
                jobName : String,
                decriptorId : Long,
                startTime : Instant,
                endTime : Option[ Instant ],
                appArgs : String )
