package b22712.gasagasa.ui.basePressure

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import b22712.gasagasa.MainApplication

class BasePressureViewModelFactory (private val application: MainApplication): ViewModelProvider.NewInstanceFactory() {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(BasePressureViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return BasePressureViewModel(application) as T
        }
        throw IllegalAccessException("unk class")
    }
}