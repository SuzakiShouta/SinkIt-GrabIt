package b22712.gasagasa.ui.game

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import b22712.gasagasa.MainApplication

class GameViewModelFactory (private val application: MainApplication): ViewModelProvider.NewInstanceFactory() {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(GameViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return GameViewModel(application) as T
        }
        throw IllegalAccessException("unk class")
    }
}