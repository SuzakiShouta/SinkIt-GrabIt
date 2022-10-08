package b22712.SinkItGrabIt.ui.game

import android.os.Handler
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import b22712.SinkItGrabIt.MainApplication
import b22712.SinkItGrabIt.ui.GetFish.GetFishFragment
import b22712.SinkItGrabIt.ui.basePressure.BasePressureFragment

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

    fun fishGrab() {

        setFishGrab(true)
        app.vibratorAction.vibrate(10000, 255)

        val hnd0 = Handler()
        val task = Runnable {
            app.vibratorAction.vibrateStop()
            setFishGrab(false)
        }
        hnd0.postDelayed(task,10000)
    }

    // 第一引数は自分自身，第二引数はどこに表示するか
    fun createGetFishFragment(fragment: Fragment, layoutId: Int) {
        app.vibratorAction.vibrateStop()
        val transaction: FragmentTransaction = fragment.parentFragmentManager.beginTransaction()
        transaction.replace(layoutId, GetFishFragment.newInstance())
        transaction.addToBackStack(null)
        transaction.commit()
    }

}