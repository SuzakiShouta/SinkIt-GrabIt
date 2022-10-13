package b22712.SinkItGrabIt.ui.game

import android.content.Intent
import android.os.Handler
import android.util.Log
import android.view.View
import b22712.SinkItGrabIt.AnimationProvider
import kotlin.math.floor

class Bubble(view: View) {

    val LOGNAME = "Bubble"
    val target = view
    var width = 1080 + 100
    var height = 2000 + 400
    var delay = floor(Math.random() * 10000).toLong()
    val animationProvider: AnimationProvider = AnimationProvider()

    val hnd0 = Handler()
    val engage = object : Runnable{
        override fun run() {
            animationProvider.bubbleX(target,width)
            animationProvider.moveY(target,height.toFloat())
            randomDelay()
            Log.d(LOGNAME,"bubble".plus(delay))
            hnd0.postDelayed(this,delay)
        }
    }

    init {
        start()
    }

    fun start(){
        hnd0.postDelayed(engage,delay)
    }

    fun randomDelay(){
        delay = floor(Math.random() * 10000).toLong() + 8000
    }

    fun setFragmentSize(w: Int, h: Int){
        width = w
        height = h
    }

}