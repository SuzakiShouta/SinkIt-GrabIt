package b22712.SinkItGrabIt

import android.content.res.TypedArray
import android.graphics.drawable.Drawable
import android.util.Log
import kotlin.math.floor

@SuppressWarnings("ResourceType")
class FishCreator(application: MainApplication) {

    val LOGNAME = "FishCreator"

    val app = application
    val shallowFishArray: TypedArray = app.applicationContext.resources.obtainTypedArray(R.array.shallow_fish)
    val deepFishArray: TypedArray = app.applicationContext.resources.obtainTypedArray(R.array.deep_fish)
    val middleFishArray: TypedArray = app.applicationContext.resources.obtainTypedArray(R.array.deep_fish)
    val shallowDepth: Float = 7F
    val deepDepth: Float = 14F

    var id: Int = 0
    var drawable: Drawable? = null
    var name: String = ""

    fun create(){
        if (app.depth.value!! < shallowDepth) {
            id = createShallowFishId()
            drawable = shallowFishArray.getDrawable(id)
            name = "fish_shallow".plus(id+1)
        } else if (app.depth.value!! > deepDepth) {
            id = createMiddleFishId()
            drawable = middleFishArray.getDrawable(id)!!
            name = "fish_middle".plus(id+1)
        } else {
            id = createDeepFishId()
            drawable = deepFishArray.getDrawable(id)!!
            name = "fish_deep".plus(id+1)
        }
        Log.d(LOGNAME,"name $name , id $id")
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

    fun createShallowFishId(): Int {
        return floor(Math.random() * shallowFishArray.length()).toInt()
    }

    fun createMiddleFishId(): Int {
        return floor(Math.random() * middleFishArray.length()).toInt()
    }

    fun createDeepFishId(): Int {
        return floor(Math.random() * deepFishArray.length()).toInt()
    }
}