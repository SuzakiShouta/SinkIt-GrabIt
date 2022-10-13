package b22712.SinkItGrabIt.ui.home

import android.os.Bundle
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import b22712.SinkItGrabIt.MainApplication
import b22712.SinkItGrabIt.ui.GetFish.GetFishFragment
import b22712.SinkItGrabIt.ui.basePressure.BasePressureFragment
import b22712.SinkItGrabIt.ui.game.GameFragment

class HomeViewModel(application: MainApplication) : ViewModel() {

    val app = application

    private val _text = MutableLiveData<String>().apply {
        value = "This is home Fragment"
    }
    val text: LiveData<String> = _text

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
            app.vibratorAction.vibrate(5000, 10)
            return false
        } else if (wasPush && !push) {
            // 押した後，離した時
            wasPush = false
            app.vibratorAction.vibrateStop()
            return true
        }
        return false
    }

}