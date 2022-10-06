package b22712.gasagasa.ui.game

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import b22712.gasagasa.databinding.ActivityGameBinding

class GameActivity : AppCompatActivity() {

    private lateinit var binding: ActivityGameBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityGameBinding.inflate(layoutInflater)
        setContentView(binding.root)


    }
}