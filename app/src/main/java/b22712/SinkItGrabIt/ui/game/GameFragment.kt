package b22712.SinkItGrabIt.ui.game

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import b22712.SinkItGrabIt.MainApplication
import b22712.SinkItGrabIt.databinding.FragmentGameBinding


class GameFragment : Fragment() {

    val LOGNAME = "GameFragment"

    private val gameViewModel: GameViewModel by viewModels{
        GameViewModelFactory((requireActivity().application as MainApplication))
    }

    private var _binding: FragmentGameBinding? = null
    private lateinit var app: MainApplication

    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        app = (requireActivity().application as MainApplication)
        _binding = FragmentGameBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        app.push.observe(viewLifecycleOwner) {
            gameViewModel.push(it)
            if(it) {
                binding.textPush.text = "押してる！"
                if(app.fishExist.value == true) {
                    binding.textFish.text = "捕獲！"
                }
            } else {
                binding.textPush.text = "押してない！"
            }
        }

        app.fishExist.observe(viewLifecycleOwner){
            if(it){
                binding.textFish.text = "魚がいます"
                app.vibratorAction.vibrate(2000, 100)
            } else {
                binding.textFish.text = "魚がいません"
            }
        }

        binding.buttonBuck.setOnClickListener {
            activity?.finish();
        }
    }

    companion object {
        @JvmStatic
        fun newInstance() =
            GameFragment().apply {
                arguments = Bundle().apply {
                }
            }
    }
}