package b22712.SinkItGrabIt.ui.GetFish

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.app.ShareCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import b22712.SinkItGrabIt.MainActivity
import b22712.SinkItGrabIt.MainApplication
import b22712.SinkItGrabIt.databinding.FragmentGetFishBinding

class GetFishFragment: Fragment() {

    val LOGNAME = "GetFishFragment"

    private val getFishViewModel: GetFishViewModel by viewModels{
        GetFishViewModelFactory((requireActivity().application as MainApplication))
    }

    private var _binding: FragmentGetFishBinding? = null
    private lateinit var app: MainApplication

    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        app = (requireActivity().application as MainApplication)
        _binding = FragmentGetFishBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.buttonBuck.setOnClickListener {
            getFragmentManager()?.beginTransaction()?.remove(this)?.commit();
        }

        binding.imageViewFish.setImageDrawable(getFishViewModel.createFish())

        binding.buttonTwitter.setOnClickListener {
            getFishViewModel.openChooserToShareThisApp(this)
        }
    }

    companion object {
        @JvmStatic
        fun newInstance() =
            GetFishFragment().apply {
                arguments = Bundle().apply {
                }
            }
    }

}