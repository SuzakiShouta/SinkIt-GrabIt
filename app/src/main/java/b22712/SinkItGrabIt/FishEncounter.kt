package b22712.SinkItGrabIt

import android.app.Application
import android.os.Handler
import android.os.Looper
import android.util.Log
import java.util.*

class FishEncounter(app: MainApplication) {

    val LOGNAME = "FishEncounter"
    val Frequency = 15
    val app: MainApplication = app

    // 魚が出現する条件：水中，気圧安定，３s
    val fishEncountMinTime: Int = 3 // 魚が出現するために必要な時間
    val fishEncountMaxTime: Int = 7 // 絶対魚が出るライン
    val fishExitTime: Long = 3000L // 出現してから逃げる時間

    // 魚が生成される数字
    var encountThreshold: Long= 3L * Frequency

    // 気圧が安定していて，水中にいる時間
    var fishAppearNum: Int = 0

    // Handler のオブジェクトを生成
    var run: Boolean = false
    private val delayMills: Long= 500L
    val handler = Handler(Looper.getMainLooper())
    private val runnable = object: Runnable {
        override fun run() {
            task()
            if(run) handler.postDelayed(this, delayMills)
        }
    }

    private fun task() {
        if (fishAppearNum > encountThreshold) { fishCreate() }
    }

    private fun fishCreate() {
        // taskを止めて数秒間魚を出現させる．終わったらtaskを動かす
        stop()
        fishAppearNum = 0
        val fishHandler = Handler(Looper.getMainLooper())
        app.setFishExist(true)
        fishHandler.postDelayed({
            app.setFishExist(false)
            start()
        },fishExitTime)
    }

    fun start() {
        setEncountNum()
        run = true
        handler.post(runnable)
    }

    fun stop() {
        run = false
    }

    fun setEncountNum () {
        val renge = (fishEncountMinTime..fishEncountMaxTime)
        encountThreshold = renge.random().toLong() * Frequency
    }

}