package b22712.SinkItGrabIt

import android.app.Application
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import java.time.LocalDateTime

class MainApplication: Application() {

    val LOGNAME: String = "MainApplication"

    // 気圧のログをとるかどうか
    private val pressureLog: Boolean = false

    // キュー
    val queue: Queue = Queue()

    lateinit var pressureSensor: PressureSensor
    lateinit var externalStorage: ExternalStorage
    lateinit var estimation: Estimation
    lateinit var vibratorAction: VibratorAction
    lateinit var fishEncounter: FishEncounter

    private val _push = MutableLiveData<Boolean>(false)
    val push: LiveData<Boolean> = _push
    fun setPush(boolean: Boolean) {
        if (push.value != boolean) {
            _push.postValue(boolean)
        }
//        Log.d(LOGNAME,"push is ".plus(push.value))
    }

    private val _grip = MutableLiveData<Boolean>(false)
    val grip: LiveData<Boolean> = _grip
    fun setGrip(boolean: Boolean) {
        if (grip.value != boolean){
            _grip.postValue(boolean)
        }
//        Log.d(LOGNAME,"push is ".plus(push.value))
    }

    // 魚が存在しているかどうか
    private val _fishExist = MutableLiveData<Boolean>(false)
    val fishExist: LiveData<Boolean> = _fishExist
    fun setFishExist(boolean: Boolean) {
        _fishExist.postValue(boolean)
        Log.d(LOGNAME,"fishExist is ".plus(fishExist.value))
    }

    // 水中かどうか
    private val _inWater = MutableLiveData<Boolean>(false)
    val inWater: LiveData<Boolean> = _inWater
    fun setInWater(boolean: Boolean) {
        if(boolean != inWater.value) {
            _inWater.postValue(boolean)
            Log.d(LOGNAME,"inWater is ".plus(boolean))
        }
    }

    // 水深
    private val _depth = MutableLiveData<Float>(0F)
    val depth: LiveData<Float> = _depth
    fun setDepth(float: Float) {
        _depth.postValue(float)
    }

    override fun onCreate() {
        super.onCreate()

        pressureSensor = PressureSensor(this)
        fishEncounter = FishEncounter(this)
        estimation = Estimation(this)
        externalStorage = ExternalStorage(applicationContext)
        vibratorAction = VibratorAction(applicationContext)

        pressureSensor.start()
        fishEncounter.start()

        if (pressureLog) {
            pressureSensor.loging = true
            externalStorage.setQueue(queue)
            externalStorage.autoSave()
        }
    }
}