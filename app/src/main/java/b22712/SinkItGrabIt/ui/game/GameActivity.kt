package b22712.SinkItGrabIt.ui.game

import android.os.Bundle
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import b22712.SinkItGrabIt.databinding.ActivityGameBinding

class GameActivity : AppCompatActivity() {

    private lateinit var binding: ActivityGameBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        window.addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON)

        binding = ActivityGameBinding.inflate(layoutInflater)
        setContentView(binding.root)


    }
}