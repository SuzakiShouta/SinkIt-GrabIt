package b22712.SinkItGrabIt

import android.content.res.TypedArray
import android.graphics.drawable.Drawable
import kotlin.math.floor

class FishCreater(application: MainApplication) {

    val app = application
    val shallowFishArray: TypedArray = app.applicationContext.resources.obtainTypedArray(R.array.shallow_fish)
    val deepFishArray: TypedArray = app.applicationContext.resources.obtainTypedArray(R.array.deep_fish)
    val depthLevel: Float = 7F

    fun create(): Drawable?{
        val rand = floor(Math.random() * shallowFishArray.length()).toInt()
        return shallowFishArray.getDrawable(rand)
    }
}