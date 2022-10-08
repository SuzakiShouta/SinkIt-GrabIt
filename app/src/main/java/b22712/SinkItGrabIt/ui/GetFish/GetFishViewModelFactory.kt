package b22712.SinkItGrabIt.ui.GetFish

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import b22712.SinkItGrabIt.MainApplication

class GetFishViewModelFactory(private val application: MainApplication): ViewModelProvider.NewInstanceFactory() {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(GetFishViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return GetFishViewModel(application) as T
        }
        throw IllegalAccessException("unk class")
    }
}