package b22712.SinkItGrabIt

import android.animation.*
import android.view.View
import android.view.animation.AccelerateInterpolator
import java.util.stream.IntStream.range
import kotlin.math.floor

class AnimationProvider {

    fun moveY(target: View, height: Float){
        val translationY = PropertyValuesHolder.ofFloat("translationY", -128f, height * -1)
        val animator = ObjectAnimator.ofPropertyValuesHolder(target, translationY).apply {
            duration = 8000
            interpolator = AccelerateInterpolator() // 急速に開始し、その後減速しながら変化させる
        }
        animator.start()
    }

    fun bubbleX(target: View, width: Int) {

        val animatorList: MutableList<Animator> = ArrayList()

        // 移動距離(translationX)は、開始位置から左右に移動距離を100, 70, 50, 40...と徐々に減らしていく
        // 前のアニメーションと繋がるように終了位置(第4引数)の値を開始位置(第3引数)として設定する

        // 時間(duration)は10, 30, 60, 100...と徐々に増やしていく
        val time: Float = 8000F
        val moveX: Int = width/10
        var x: Float = floor(Math.random() * width).toFloat() - (width/2)
        repeat(10) {
            val randX = floor(Math.random() * moveX).toInt() - (moveX/2)
            animatorList.add(ObjectAnimator.ofFloat(target, "translationX", x, x+randX)
                .setDuration((time/10).toLong()))
            x += randX
        }

        val set = AnimatorSet()
        set.playSequentially(animatorList) // 順番にアニメーションを実施
        set.start()
    }
}