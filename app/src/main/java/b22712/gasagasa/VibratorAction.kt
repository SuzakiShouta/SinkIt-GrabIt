package b22712.gasagasa

import android.content.Context
import android.os.VibrationEffect
import android.os.Vibrator
import android.os.VibratorManager

class VibratorAction (context: Context){

    val context: Context = context
    var vibrator: Vibrator

    init {
        val vibratorManager = if (android.os.Build.VERSION.SDK_INT >= 31) {
            context.getSystemService(Context.VIBRATOR_MANAGER_SERVICE) as VibratorManager
        } else {
            TODO("VERSION.SDK_INT < 31")
        }
        vibrator = vibratorManager.defaultVibrator
    }

    fun vibrate(ms: Long, amp: Int) {
        val vibrationEffect = VibrationEffect.createOneShot(ms, amp)
        vibrator.vibrate(vibrationEffect)
    }

    fun vibrateStop() {
        vibrator.cancel()
    }
}