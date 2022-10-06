package b22712.gasagasa.ui.basePressure

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import b22712.gasagasa.MainApplication
import b22712.gasagasa.databinding.FragmentBasePressureBinding
import b22712.gasagasa.databinding.FragmentHomeBinding

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

        binding.buttonBasePressureSetting.setOnClickListener {
            binding.buttonBasePressureSetting.isClickable = false
            basePressureViewModel.setBasePressure(this)
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