package com.example.pokeapp.presentation.ui.auth.login

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.pokeapp.R
import com.example.pokeapp.databinding.FragmentLoginBinding
import com.example.pokeapp.domain.preferences.SharedPreferences
import com.example.pokeapp.domain.resource.Resource
import com.example.pokeapp.presentation.ui.main.MainActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LoginFragment : Fragment(R.layout.fragment_login) {
    private var _binding: FragmentLoginBinding? = null
    private val binding get() = _binding!!
    private lateinit var viewModel: LoginViewModel
    private lateinit var preferences: SharedPreferences

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentLoginBinding.bind(view)
        viewModel = ViewModelProvider(this)[LoginViewModel::class.java]
        preferences = SharedPreferences(requireContext())
        binding.lifecycleOwner = viewLifecycleOwner
        binding.viewModel = viewModel
        binding.createAccountLink.setOnClickListener {
            val action = LoginFragmentDirections.actionNavigationLoginToNavigationRegister()
            findNavController().navigate(action)
        }
        binding.forgotAccountLink.setOnClickListener {
            viewModel.resetPassword()
        }
        binding.buttonContinue.setOnClickListener {
            viewModel.loginUser()
        }

        observeResponse()
    }

    private fun observeResponse() {

        viewModel.loginLiveData.observe(viewLifecycleOwner) {
            val response = it ?: return@observe
            when (response) {
                is Resource.Success -> {
                    Toast.makeText(
                        requireContext(),
                        getString(R.string.welcome),
                        Toast.LENGTH_LONG
                    ).show()
                    preferences.setData(true, SharedPreferences.LOGGED_IN)
                    startActivity(Intent(context, MainActivity::class.java))
                    this@LoginFragment.activity?.finish()
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

        viewModel.resetPasswordLiveData.observe(viewLifecycleOwner) {
            val response = it ?: return@observe
            when (response) {
                is Resource.Success -> {
                    Toast.makeText(
                        requireContext(),
                        "Se envio el correo para agregar una nueva contraseña",
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