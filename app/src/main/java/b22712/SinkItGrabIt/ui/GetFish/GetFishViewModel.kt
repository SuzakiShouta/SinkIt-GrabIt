package b22712.SinkItGrabIt.ui.GetFish

import android.content.res.Resources
import android.content.res.TypedArray
import android.graphics.drawable.Drawable
import androidx.core.content.res.ResourcesCompat
import androidx.lifecycle.ViewModel
import b22712.SinkItGrabIt.MainApplication
import b22712.SinkItGrabIt.R
import kotlin.math.floor

class GetFishViewModel(application: MainApplication) : ViewModel(){

    val app = application
    val shallowFishArray: TypedArray = app.applicationContext.resources.obtainTypedArray(R.array.shallow_fish)
    val deepFishArray: TypedArray = app.applicationContext.resources.obtainTypedArray(R.array.deep_fish)
    val depthLevel: Float = 7F

    fun createFish(): Drawable? {
        return if (app.depth.value!! < depthLevel) {
            createShallowFish()
        } else {
            createDeepFish()
        }
    }

    fun createShallowFish(): Drawable? {
        val rand = floor(Math.random() * shallowFishArray.length()).toInt()
        return shallowFishArray.getDrawable(rand)
    }

    fun createDeepFish(): Drawable? {
        val rand = floor(Math.random() * deepFishArray.length()).toInt()
        return deepFishArray.getDrawable(rand)
    }
}
