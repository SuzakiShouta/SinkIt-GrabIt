package b22712.SinkItGrabIt

import android.util.Log

class Estimation(application: MainApplication) {

    val LOGNAME: String = "Estimation"

    val app: MainApplication = application
    val queue: Queue = application.queue
    val fishEncounter: FishEncounter = app.fishEncounter
    var basePressure: Float = 1013.0F
    val frequency = 15

    // 押す
    var push: Boolean = false
    // 握る
    var grip: Boolean = false
    val pushThreshold: Int = 5 // hPa
    val gripThreshold: Int = 3 // hPa
//    val takeOffThreshold: Int = pushThreshold - 1
    var pushTime: Int  = frequency/5
    var pressureStability: Boolean = false // 安定しているか
    var pressureStabilityThreshold: Int = 1 // hPa
    var pressureRelative: Float = 1013.3F
    var pressureRelativeNum: Int = frequency/3 //何データ見るか

    var isInWater: Boolean = false
    val inWaterThreshold: Float = 1F //hPa

    private fun isStabile() {
        var min: Float = 99999.9F
        var max: Float = 0.0F
        if (queue.queue.size > queue.queueCapacity - 1) {
            for (i: Int in 10 - pressureRelativeNum - pushTime until 9 - pushTime) {
                val p = queue.queue[i]
                if (min > p) {
                    min = p
                }
                if (max < p) {
                    max = p
                }
            }
            if (max - min < pressureStabilityThreshold) {
                pressureStability = true
                pressureRelative = queue.queue[9 - pushTime]
            } else {
                pressureStability = false
            }
        }
    }

    private fun isGrab() {
        // grab推定履歴の数データをみて一個でもtrueになってたらgrabできなくなる

        var wasGrab: Boolean = false
        for (bool in queue.grabQueue) {
            if (bool) {
                wasGrab = true
            }
        }

        // 押す
        if (queue.queue.last() > pressureRelative + pushThreshold && pressureStability && !wasGrab) {
            push = true
        }
        if (queue.queue.last() < pressureRelative - pushThreshold && push) {
            push = false
        }
        // 握る
        if (queue.queue.last() < pressureRelative + gripThreshold && pressureStability && !wasGrab) {
            grip = true
        }
        if (queue.queue.last() > pressureRelative - gripThreshold && grip) {
            grip = false
        }

        if (push || grip) {
            app.queue.addGrabQueue(true)
        } else {
            app.queue.addGrabQueue(false)
        }

        app.setPush(push)
        app.setGrip(grip)
    }

    private fun isInWater() {
//        Log.d(LOGNAME, "Base = $basePressure, now ${queue.queue.last()}")
        if (pressureStability) {
            isInWater = queue.queue.last() > basePressure + inWaterThreshold
            app.setInWater(isInWater)
        } else {
            app.setInWater(isInWater)
        }
    }

    fun fishAppear() {
//        Log.d(LOGNAME, "stability $pressureStability, isInwater $isInWater")
        if (pressureStability && isInWater) {
            fishEncounter.fishAppearNum++
        } else {
            fishEncounter.fishAppearNum = 0
        }
    }

    fun setDepth() {
        if (pressureStability) {
            app.setDepth(queue.queue.last() - basePressure)
        }
    }

    fun estimation(){
        isStabile()
        isGrab()
        isInWater()
        fishAppear()
        setDepth()
    }

    fun setBasePressure(queue: ArrayDeque<Float>) {
        var sum: Float = 0F
        for (pressure in queue) {
            sum += pressure
//            Log.d(LOGNAME, "pressure = ".plus(pressure))
        }
        basePressure = sum / (queue.size)
        Log.d(LOGNAME, "basePressure = $basePressure, sum = $sum, size = ${queue.size}")
    }


}