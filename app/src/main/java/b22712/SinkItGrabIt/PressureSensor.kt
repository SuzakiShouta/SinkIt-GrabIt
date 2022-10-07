package b22712.SinkItGrabIt

import android.content.Context
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import android.util.Log

class PressureSensor(application: MainApplication): SensorEventListener {

    private val LOGNAME = "PressureSensor"
    val app: MainApplication = application

    val context = application.applicationContext
    val queue = application.queue
    private var sensorManager: SensorManager
    private var pressureSensor: Sensor? = null
    var loging: Boolean = false
    var basePressureLog: Boolean = false

    init {
        sensorManager = context.getSystemService(Context.SENSOR_SERVICE) as SensorManager
        pressureSensor = sensorManager.getDefaultSensor(Sensor.TYPE_PRESSURE)
    }

    fun start() {
        sensorManager.registerListener(this, pressureSensor, SensorManager.SENSOR_DELAY_UI)
    }

    fun stop() {
        sensorManager.unregisterListener(this)
    }

    override fun onSensorChanged(event: SensorEvent) {
        var pressure: Float = event.values[0]
        queue.addQueue(pressure)
        if(loging) { save(pressure) }
        if(basePressureLog) { queue.addBasePressureQueue(pressure)
            Log.d(LOGNAME, queue.basePressureQueue.size.toString())
        }
        app.estimation.estimation()
//        Log.d(LOGNAME, pressure.toString())
    }

    override fun onAccuracyChanged(p0: Sensor?, p1: Int) {
    }

    private fun save(value: Float) {
        queue.stringQueue.add(getUnixTime().toString().plus(",").plus(value))
        Log.d(LOGNAME,queue.stringQueue.size.toString())
    }
    fun getUnixTime() : Long {
        return System.currentTimeMillis()
    }

}