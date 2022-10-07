package b22712.SinkItGrabIt.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import b22712.SinkItGrabIt.MainApplication

class HomeViewModelFactory (private val application: MainApplication): ViewModelProvider.NewInstanceFactory() {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(HomeViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return HomeViewModel(application) as T
        }
        throw IllegalAccessException("unk class")
    }
}