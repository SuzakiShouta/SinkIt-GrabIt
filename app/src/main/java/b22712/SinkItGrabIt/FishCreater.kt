package b22712.SinkItGrabIt

import android.content.res.TypedArray
import android.graphics.drawable.Drawable
import kotlin.math.floor

class FishCreater(application: MainApplication) {

    val app = application
    val shallowFishArray: TypedArray = app.applicationContext.resources.obtainTypedArray(R.array.shallow_fish)
    val deepFishArray: TypedArray = app.applicationContext.resources.obtainTypedArray(R.array.deep_fish)
    val middleFishArray: TypedArray = app.applicationContext.resources.obtainTypedArray(R.array.deep_fish)
    val shallowDepth: Float = 7F
    val deepDepth: Float = 14F

    fun create(): Drawable?{
        if (app.depth.value!! < shallowDepth) {
            return createShallowFish()
        } else if (app.depth.value!! > deepDepth) {
            return createDeepFish()
        } else {
            return createMiddleFish()
        }
    }

    fun createShallowFish(): Drawable? {
        val rand = floor(Math.random() * shallowFishArray.length()).toInt()
        return shallowFishArray.getDrawable(rand)
    }

    fun createMiddleFish(): Drawable? {
        val rand = floor(Math.random() * middleFishArray.length()).toInt()
        return middleFishArray.getDrawable(rand)
    }

    fun createDeepFish(): Drawable? {
        val rand = floor(Math.random() * deepFishArray.length()).toInt()
        return deepFishArray.getDrawable(rand)
    }
}