package b22712.SinkItGrabIt.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import b22712.SinkItGrabIt.MainApplication
import b22712.SinkItGrabIt.databinding.FragmentHomeBinding

class HomeFragment : Fragment() {

    val LOGNAME: String = "HomeFragment"

    private val homeViewModel: HomeViewModel by viewModels{
        HomeViewModelFactory((requireActivity().application as MainApplication))
    }

    private var _binding: FragmentHomeBinding? = null
    private lateinit var app: MainApplication

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        app = (requireActivity().application as MainApplication)

        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val textView: TextView = binding.textHome
        homeViewModel.text.observe(viewLifecycleOwner) {
            textView.text = it
        }
        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        binding.buttonSetPressure.setOnClickListener {
            homeViewModel.viewBasePressureFragment(this, binding.basePressureLayout.id)
        }

        app.push.observe(viewLifecycleOwner){
            if(homeViewModel.startGamePreparation(it)) {
                homeViewModel.viewBasePressureFragment(this, binding.basePressureLayout.id)
            }
        }

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}