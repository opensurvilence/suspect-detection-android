package org.yash10019coder.suspectdetectionxml.ui.suspect.add

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import org.yash10019coder.suspectdetectionxml.databinding.FragmentAddSuspectBinding

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [AddSuspectFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class AddSuspectFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    private lateinit var binding: FragmentAddSuspectBinding
    private lateinit var addSuspectViewModel: AddSuspectViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        // Inflate the layout for this fragment

        binding = FragmentAddSuspectBinding.inflate(inflater, container, false)
        addSuspectViewModel = ViewModelProvider(this).get(AddSuspectViewModel::class.java)

        val photoPicker =
            registerForActivityResult(ActivityResultContracts.PickVisualMedia()) { uri ->
                if (uri != null)
                    binding.ivSuspectImage.setImageURI(uri)
            }


        binding.flAddImage.setOnClickListener {
            photoPicker.launch(PickVisualMediaRequest(ActivityResultContracts.PickVisualMedia.ImageOnly))
        }

        /*binding.tilSuspectName.addOnEditTextAttachedListener {
            if (it.editText.toString().isNotEmpty()) {
                addSuspectViewModel.name.set(it.editText.toString())
                it.error = null
            } else {
                it.error = "Name is required"
            }
        }

        binding.tilSuspectAge.addOnEditTextAttachedListener {
            if (it.editText.toString().isNotEmpty()) {
                addSuspectViewModel.age.set(it.editText.toString().toInt())
                it.error = null
            } else {
                it.error = "Age is required"
            }
        }

        binding.tilSuspectPlace.addOnEditTextAttachedListener {
            if (it.editText.toString().isNotEmpty()) {
                addSuspectViewModel.place.set(it.editText.toString())
                it.error = null
            } else {
                it.error = "Place is required"
            }
        }

        binding.tilSuspectLocation.addOnEditTextAttachedListener {
            if (it.editText.toString().isNotEmpty()) {
                addSuspectViewModel.location.set(it.editText.toString())
                it.error = null
            } else {
                it.error = "Location is required"
            }
        }

        binding.tilSuspectTime.addOnEditTextAttachedListener {
            if (it.editText.toString().isNotEmpty()) {
                addSuspectViewModel.timeUnixTimestamp.set(it.editText.toString().toLong())
                it.error = null
            } else {
                it.error = "Time is required"
            }
        }*/

        binding.btnAddSuspect.setOnClickListener {

        }

        return binding.root
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment AddSuspectFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            AddSuspectFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}
