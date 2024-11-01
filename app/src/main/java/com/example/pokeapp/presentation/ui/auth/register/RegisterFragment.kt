package com.example.pokeapp.presentation.ui.auth.register

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.pokeapp.R
import com.example.pokeapp.databinding.FragmentRegisterBinding
import com.example.pokeapp.domain.resource.Resource
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class RegisterFragment : Fragment(R.layout.fragment_register) {

    private var _binding: FragmentRegisterBinding? = null
    private val binding get() = _binding!!
    private lateinit var viewModel: RegisterViewModel

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentRegisterBinding.bind(view)
        binding.lifecycleOwner = viewLifecycleOwner
        viewModel = ViewModelProvider(this)[RegisterViewModel::class.java]
        binding.viewModel = viewModel
        binding.buttonContinue.setOnClickListener {
            viewModel.registerUser()
        }

        observeResponse()
    }

    private fun observeResponse() {

        viewModel.registerLiveData.observe(viewLifecycleOwner) {
            val response = it ?: return@observe
            when (response) {
                is Resource.Success -> {
                    findNavController().navigateUp()
                }
                is Resource.Failure -> {
                    Toast.makeText(
                        requireContext(),
                        response.errorBody.toString(),
                        Toast.LENGTH_SHORT
                    ).show()
                }
                is Resource.Loading -> {
                }
            }
        }

        viewModel.sendEmailLiveData.observe(viewLifecycleOwner) {
            val response = it ?: return@observe
            when (response) {
                is Resource.Success -> {
                    Toast.makeText(
                        requireContext(),
                        "Se envio correctamente el correo",
                        Toast.LENGTH_SHORT
                    ).show()
                }
                is Resource.Failure -> {
                    Toast.makeText(
                        requireContext(),
                        response.errorBody.toString(),
                        Toast.LENGTH_SHORT
                    ).show()
                }
                is Resource.Loading -> {}
            }
        }
    }


}