package b22712.SinkItGrabIt

import android.util.Log

class Estimation(application: MainApplication) {

    val LOGNAME: String = "Estimation"

    val app: MainApplication = application
    val queue: Queue = application.queue
    val fishEncounter: FishEncounter = app.fishEncounter
    var basePressure: Float = 1013.0F

    var push: Boolean = false
    val pushThreshold: Int = 5 // hPa
//    val takeOffThreshold: Int = pushThreshold - 1
    var pushTime: Int  = 3 // 15Hz
    var pressureStability: Boolean = false // 安定しているか
    var pressureStabilityThreshold: Int = 1 // hPa
    var pressureRelative: Float = 1013.3F
    var pressureRelativeNum: Int = 5 //何データ見るか

    var isInWater: Boolean = false
    val inWaterThreshold: Int = 1 //hPa

    private fun isStabile() {
        var min: Float = 99999.9F
        var max: Float = 0.0F
        for (i:Int in 10-pressureRelativeNum-pushTime until 9-pushTime) {
            val p = queue.queue[i]
            if (min > p) { min = p }
            if (max < p) { max = p }
        }
        if (max - min < pressureStabilityThreshold) {
            pressureStability = true
            pressureRelative = queue.queue[9-pushTime]
        } else {
            pressureStability = false
        }
    }

    private fun isPush(){

        if (queue.queue.last() > pressureRelative + pushThreshold && pressureStability) {
            push = true
        }
        if (queue.queue.last() < pressureRelative - pushThreshold && push) {
            push = false
        }
        app.setPush(push)
//        Log.d(LOGNAME,"stability: ".plus(pressureStability).plus(", isPush: ").plus(push))
//        Log.d(LOGNAME,queue.queue.last().toString()
//            .plus(" - ").plus(pressureRelative)
//            .plus(" = ").plus(queue.queue.last()-pressureRelative))
    }

    private fun isInWater() {
        if (pressureStability) {
            isInWater = queue.queue.last() < basePressure - inWaterThreshold
            app.setInWater(true)
        } else {
            app.setInWater(false)
        }
    }

    fun fishAppear() {
        if (pressureStability && isInWater) {
            fishEncounter.fishAppearNum++
        } else {
            fishEncounter.fishAppearNum = 0
        }
    }

    fun estimation(){
        isStabile()
        isPush()
        isInWater()
        fishAppear()
    }

    fun setBasePressure(queue: ArrayDeque<Float>) {
        var sum: Float = 0F
        for (pressure in queue) {
            sum += pressure
        }
        basePressure = sum / queue.size
        Log.d(LOGNAME, "basePressure = ".plus(basePressure))
    }


}