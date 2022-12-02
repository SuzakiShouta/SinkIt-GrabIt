package b22712.SinkItGrabIt.ui.game

import android.os.Handler
import android.util.Log
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import b22712.SinkItGrabIt.AnimationProvider
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

    var width = 1080 + 200
    var height = 2000 + 400
    fun setFragmentSize(view: View) {
        view?.post {
            width = view?.width + 100
            height = view?.height + 400
            Log.d(LOGNAME, width.toString().plus(",").plus(height))
            for(bubble in bubbles){
                bubble.setFragmentSize(width,height)
            }
        }
    }

    var bubbles: ArrayList<Bubble> = arrayListOf()
    fun bubble(view: View) {
        val bubble = Bubble(view)
        bubble.setFragmentSize(width,height)
        bubbles.add(bubble)
    }

    fun bubbleCrash(){
        var bubbles: ArrayList<Bubble> = arrayListOf()
    }

    fun push(push: Boolean) {
        if(push){
            // 魚がいたら握れる，強くバイブ
            if(app.fishExist.value == true){
                fishGrab()
            }
        } else if (isFishGrab.value == true && !push) {
//            Log.d(LOGNAME, "離しちゃった")
//            setFishGrab(false)
//            app.vibratorAction.vibrateStop()
        }
    }

    //
    var wasGrab: Boolean = false
    fun fishSilhouette(grab: Boolean, view: View) {
        if (!wasGrab && grab) {
            Log.d(LOGNAME, "Re")
            view.visibility = View.VISIBLE
            view.alpha = 1.0f
            AnimationProvider().fadeIn(view, 1)
            Log.d(LOGNAME, view.alpha.toString().plus(",").plus(view.visibility))
        } else if (wasGrab && !grab) {
            Log.d(LOGNAME, "fadeout")
            view.visibility = View.INVISIBLE
            AnimationProvider().fadeOut(view, 1000)
            Log.d(LOGNAME, view.alpha.toString().plus(",").plus(view.visibility))
        }
        wasGrab = grab
    }

    fun fishGrab() {

        setFishGrab(true)
        app.vibratorAction.vibrate(5000, 255)

        val hnd0 = Handler()
        val task = Runnable {
            if (isFishGrab.value == true) {
                app.vibratorAction.vibrateStop()
                setFishGrab(false)
                Log.d(LOGNAME, "逃げちゃった")
            }
        }
        hnd0.postDelayed(task,5000)
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