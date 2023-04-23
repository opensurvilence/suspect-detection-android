package org.yash10019coder.suspectdetectionxml.ui.suspect.add

import android.os.Bundle
import android.util.Base64
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.snackbar.Snackbar
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.yash10019coder.suspectdetectionxml.data.Result
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
                if (uri != null) {
                    binding.ivSuspectImage.setImageURI(uri)

                    CoroutineScope(Dispatchers.Main).launch {
                        val base64 = withContext(Dispatchers.IO) {
                            val inputStream = requireActivity().contentResolver.openInputStream(uri)
                            val bytes = inputStream?.let { ByteArray(it.available()) }
                            inputStream?.read(bytes)
                            Base64.encodeToString(bytes, Base64.DEFAULT)
                        }
                        addSuspectViewModel.imageBase64.set(base64)
                    }
                }
            }


        binding.flAddImage.setOnClickListener {
            photoPicker.launch(PickVisualMediaRequest(ActivityResultContracts.PickVisualMedia.ImageOnly))
        }

        binding.tilSuspectName.editText?.addTextChangedListener {
            if (it.toString().isNotEmpty()) {
                addSuspectViewModel.name.set(it.toString())
                binding.tilSuspectName.error = null
            } else {
                binding.tilSuspectName.error = "Name is required"
            }
        }

        binding.tilSuspectPlace.editText?.addTextChangedListener {
            if (it.toString().isNotEmpty()) {
                addSuspectViewModel.place.set(it.toString())
                binding.tilSuspectPlace.error = null
            } else {
                binding.tilSuspectPlace.error = "Place is required"
            }
        }

        binding.tilSuspectAge.editText?.addTextChangedListener {
            if (it.toString().isNotEmpty()) {
                addSuspectViewModel.age.set(it.toString().toInt())
                binding.tilSuspectAge.error = null
            } else {
                binding.tilSuspectAge.error = "Age is required"
            }
        }

        binding.tilSuspectLocation.editText?.addTextChangedListener {
            if (it.toString().isNotEmpty()) {
                addSuspectViewModel.location.set(it.toString())
                binding.tilSuspectLocation.error = null
            } else {
                binding.tilSuspectLocation.error = "Location is required"
            }
        }

        binding.tilSuspectTime.editText?.addTextChangedListener {
            if (it.toString().isNotEmpty()) {
                addSuspectViewModel.timeUnixTimestamp.set(it.toString().toLong())
                binding.tilSuspectTime.error = null
            } else {
                binding.tilSuspectTime.error = "Time is required"
            }
        }

        binding.btnAddSuspect.setOnClickListener {
            CoroutineScope(Dispatchers.Main).launch {
                val result = addSuspectViewModel.addSuspect()
                if (result is Result.Success) {
                    Snackbar.make(binding.root, "Suspect added successfully", Snackbar.LENGTH_SHORT)
                        .show()
                } else if (result is Result.Error) {
                    Snackbar.make(binding.root, "Error adding suspect", Snackbar.LENGTH_SHORT)
                        .show()
                }
            }
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
