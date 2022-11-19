package b22712.SinkItGrabIt.ui.home

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import b22712.SinkItGrabIt.MainApplication
import b22712.SinkItGrabIt.R
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

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        homeViewModel.setFragmentSize(view)
        homeViewModel.bubble(binding.bubble6)
        homeViewModel.bubble(binding.bubble7)
        homeViewModel.bubble(binding.bubble8)
        homeViewModel.bubble(binding.bubble9)
        homeViewModel.bubble(binding.bubble10)

        homeViewModel.viewBasePressureFragment(this, binding.basePressureLayout.id)

        binding.buttonSetPressure.setOnClickListener {
            homeViewModel.viewBasePressureFragment(this, binding.basePressureLayout.id)
        }

        app.push.observe(viewLifecycleOwner){
            if(homeViewModel.startGamePreparation(it)) {
                homeViewModel.startGame(this)
            }
            if(it){
                binding.imageView.setImageResource(R.drawable.grab_it)
                binding.textViewStart.text = "離して！"
            } else {
                binding.imageView.setImageResource(R.drawable.grab_not)
                binding.textViewStart.text = "画面を強く押して\nスタート！"
            }
        }

        app.grip.observe(viewLifecycleOwner) {
            if(homeViewModel.startGamePreparationByGrip(it)) {
                homeViewModel.startGame(this)
            }
            if(it){
                binding.imageView.setImageResource(R.drawable.grab_it)
                binding.textViewStart.text = "離して！"
            } else {
                binding.imageView.setImageResource(R.drawable.grab_not)
                binding.textViewStart.text = "画面を強く押して\nスタート！"
            }
        }

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}