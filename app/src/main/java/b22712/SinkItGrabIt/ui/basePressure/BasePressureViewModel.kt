package b22712.SinkItGrabIt.ui.basePressure


import android.content.Intent
import android.os.Handler
import android.util.Log
import android.widget.TextView
import androidx.core.content.ContextCompat.startActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import b22712.SinkItGrabIt.MainApplication
import b22712.SinkItGrabIt.ui.game.GameActivity

class BasePressureViewModel(application: MainApplication) : ViewModel(){

    val LOGNAME = "BasePressureViewModel"

    val app = application

    fun startGame(fragment: Fragment){
        val intent = Intent(app.applicationContext, GameActivity::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
        app.applicationContext.startActivity(intent)
    }

    fun setBasePressure(fragment: Fragment, textView: TextView){

        val hnd0 = Handler()
        //最後に実行されるやつ
        val replaceGameFragment = Runnable {
//            val intent = Intent(app.applicationContext, GameActivity::class.java)
//            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
//            app.applicationContext.startActivity(intent)
            fragment.getFragmentManager()?.beginTransaction()?.remove(fragment)?.commit();
        }
        //次に実行されるやつ
        val stopBasePressureTask = Runnable {
            app.pressureSensor.basePressureLog = false
            textView.text = "設定完了"
            Log.d(LOGNAME,app.queue.basePressureQueue.size.toString())
            app.estimation.setBasePressure(app.queue.basePressureQueue)
            hnd0.postDelayed(replaceGameFragment, 1000)
        }
        //最初に実行されるやつ
        val startBasePressureTask = Runnable {
            app.pressureSensor.basePressureLog = true
            textView.text = "設定中"
            hnd0.postDelayed(stopBasePressureTask, 3000)
        }
        hnd0.postDelayed(startBasePressureTask,1000)

    }

}