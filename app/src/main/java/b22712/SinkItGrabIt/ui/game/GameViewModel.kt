package b22712.SinkItGrabIt.ui.game

import android.os.Handler
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import b22712.SinkItGrabIt.MainApplication

class GameViewModel(application: MainApplication) : ViewModel() {

    val LOGNAME = "GameViewModel"
    val app = application

    // 魚が握ったかどうか
    private val _isFishGrab = MutableLiveData<Boolean>(false)
    val isFishGrab: LiveData<Boolean> = _isFishGrab
    fun setFishGrab(boolean: Boolean) {
        _isFishGrab.postValue(boolean)
    }

    fun push(push: Boolean) {
        if(push){
            // 魚がいたら握れる，強くバイブ
            if(app.fishExist.value == true){
                fishGrab()
            }
            setFishGrab(true)
        } else {
            app.vibratorAction.vibrateStop()
        }
    }

    fun inWater(isInWater: Boolean) {
        //魚を握った状態で水中から出たらゲット
        if (!isInWater && isFishGrab.value!!) {

        }
    }

    fun fishGrab() {

        setFishGrab(true)
        app.vibratorAction.vibrate(3000, 255)

        val hnd0 = Handler()
        val task = Runnable {
            app.vibratorAction.vibrateStop()
            setFishGrab(false)
        }
        hnd0.postDelayed(task,3000)
    }

}