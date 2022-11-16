package b22712.SinkItGrabIt.ui.basePressure

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.viewModels
import b22712.SinkItGrabIt.MainApplication
import b22712.SinkItGrabIt.databinding.FragmentBasePressureBinding


class BasePressureFragment : Fragment() {

    val LOGNAME = "BasePressureFragment"

    private val basePressureViewModel: BasePressureViewModel by viewModels{
        BasePressureViewModelFactory((requireActivity().application as MainApplication))
    }

    private var _binding: FragmentBasePressureBinding? = null
    private lateinit var app: MainApplication

    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        app = (requireActivity().application as MainApplication)
        _binding = FragmentBasePressureBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

//        basePressureViewModel.setBasePressure(this, binding.textViewSettingNow)

        binding.buttonSetting.setOnClickListener {
            binding.buttonSetting.isEnabled = false
            basePressureViewModel.setBasePressure(this, binding.textViewSettingNow)
        }

    }

    companion object {
        @JvmStatic
        fun newInstance() =
            BasePressureFragment().apply {
                arguments = Bundle().apply {
                }
            }
    }

}