package b22712.SinkItGrabIt.ui.home

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import b22712.SinkItGrabIt.MainApplication
import b22712.SinkItGrabIt.ui.GetFish.GetFishFragment
import b22712.SinkItGrabIt.ui.basePressure.BasePressureFragment
import b22712.SinkItGrabIt.ui.game.Bubble
import b22712.SinkItGrabIt.ui.game.GameActivity
import b22712.SinkItGrabIt.ui.game.GameFragment

class HomeViewModel(application: MainApplication) : ViewModel() {

    val app = application
    val LOGNAME = "HomeViewModel"

    fun viewBasePressureFragment(fragment: Fragment, layoutId: Int) {
        val transaction: FragmentTransaction = fragment.parentFragmentManager.beginTransaction()
        transaction.replace(layoutId, BasePressureFragment.newInstance())
        transaction.addToBackStack(null)
        transaction.commit()
    }

    private var wasPush = false
    fun startGamePreparation(push: Boolean): Boolean{
        if(!wasPush && push){
            // 押す前，押した時
            wasPush = true
            app.vibratorAction.vibrate(3000, 100)
            return false
        } else if (wasPush && !push) {
            // 押した後，離した時
            wasPush = false
//            app.vibratorAction.vibrateStop()
            return true
        }
        return false
    }

    private var wasGrip = false
    fun startGamePreparationByGrip(grip: Boolean): Boolean{
        if(!wasGrip && grip){
            // 押す前，押した時
            wasGrip = true
            app.vibratorAction.vibrate(3000, 100)
            return false
        } else if (wasGrip && !grip) {
            // 押した後，離した時
            wasGrip = false
//            app.vibratorAction.vibrateStop()
            return true
        }
        return false
    }

    fun startGame(fragment: Fragment){
        val intent = Intent(app.applicationContext, GameActivity::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
        app.applicationContext.startActivity(intent)
        app.vibratorAction.vibrateStop()
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

}