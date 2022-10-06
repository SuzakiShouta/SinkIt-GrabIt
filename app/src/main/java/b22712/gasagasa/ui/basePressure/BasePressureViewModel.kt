package b22712.gasagasa.ui.basePressure


import android.content.Intent
import android.os.Handler
import android.util.Log
import androidx.core.content.ContextCompat.startActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import b22712.gasagasa.MainApplication
import b22712.gasagasa.ui.game.GameActivity

class BasePressureViewModel(application: MainApplication) : ViewModel(){

    val LOGNAME = "BasePressureViewModel"

    val app = application


    fun setBasePressure(fragment: Fragment){

        val hnd0 = Handler()
        val replaceGameFragment = object : Runnable {
            override fun run() {
                val intent = Intent(app.applicationContext, GameActivity::class.java)
                intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
                app.applicationContext.startActivity(intent)
                fragment.getFragmentManager()?.beginTransaction()?.remove(fragment)?.commit();
            }
        }
        val stopBasePressureTask = object : Runnable {
            override fun run() {
                hnd0.postDelayed(replaceGameFragment, 1000)
            }
        }
        val startBasePressureTask = object : Runnable {
            override fun run() {
                hnd0.postDelayed(stopBasePressureTask, 1000)
            }
        }
        hnd0.postDelayed(startBasePressureTask,1000)

    }

}