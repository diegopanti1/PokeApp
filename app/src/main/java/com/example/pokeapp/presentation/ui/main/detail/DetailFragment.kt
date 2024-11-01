package com.example.pokeapp.presentation.ui.main.detail

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.navArgs
import com.example.pokeapp.R
import com.example.pokeapp.databinding.FragmentDetailBinding
import com.example.pokeapp.domain.resource.Resource
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailFragment : Fragment(R.layout.fragment_detail) {
    private var _binding: FragmentDetailBinding? = null
    private val binding get() = _binding!!
    private val args: DetailFragmentArgs by navArgs()
    private lateinit var viewModel: DetailViewModel

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentDetailBinding.bind(view)
        viewModel = ViewModelProvider(this)[DetailViewModel::class.java]
        binding.lifecycleOwner = viewLifecycleOwner
        viewModel.getPokemon(args.name)
        observeResponse()
    }

    private fun observeResponse() = lifecycleScope.launchWhenStarted {

        viewModel.pokemon.observe(viewLifecycleOwner) {
            val response = it ?: return@observe
            when (response) {
                is Resource.Success -> {
                    binding.model = response.value
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
    }
}