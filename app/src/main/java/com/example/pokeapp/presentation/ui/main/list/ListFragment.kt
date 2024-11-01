package com.example.pokeapp.presentation.ui.main.list

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.pokeapp.R
import com.example.pokeapp.databinding.FragmentListBinding
import com.example.pokeapp.domain.preferences.SharedPreferences
import com.example.pokeapp.domain.resource.Resource
import com.example.pokeapp.presentation.adapter.PokeRecyclerViewAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ListFragment : Fragment(R.layout.fragment_list) {

    private var _binding: FragmentListBinding? = null
    private val binding get() = _binding!!
    private val viewModel: ListViewModel by activityViewModels()
    private lateinit var preferences: SharedPreferences
    private val pokemonAdapter = PokeRecyclerViewAdapter()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentListBinding.bind(view)
        preferences = SharedPreferences(requireContext())
        binding.lifecycleOwner = viewLifecycleOwner

        binding.pokeList.apply {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(requireActivity())
            adapter = pokemonAdapter
        }
        binding.fab.setOnClickListener {
            preferences.setData(false, SharedPreferences.LOGGED_IN)
            this@ListFragment.activity?.finishAffinity()
        }
        binding.fabLoad.setOnClickListener {
            viewModel.getPokemons(10)
        }
        pokemonAdapter.itemClickListener = { _, item, _ ->
            val action = ListFragmentDirections.actionNavigationListToNavigationDetail(item.name)
            findNavController().navigate(action)
        }

        viewModel.getPokemons()
        observeResponse()
    }

    private fun observeResponse() = lifecycleScope.launchWhenStarted {

        viewModel.pokemons.observe(viewLifecycleOwner) {
            val response = it ?: return@observe
            when (response) {
                is Resource.Success -> {
                    pokemonAdapter.submitList(response.value.results)
                }

                is Resource.Failure -> {
                    Toast.makeText(
                        requireContext(),
                        response.errorBody.toString(),
                        Toast.LENGTH_SHORT
                    ).show()
                }

                is Resource.Loading -> {
                    binding.loading = true
                }
            }

            if (!response.isLoading) {
                binding.loading = false
            }
        }

        viewModel.pokemonsOffset.observe(viewLifecycleOwner) {
            val response = it ?: return@observe
            when (response) {
                is Resource.Success -> {
                    pokemonAdapter.addList(response.value.results)
                }

                is Resource.Failure -> {
                    Toast.makeText(
                        requireContext(),
                        response.errorBody.toString(),
                        Toast.LENGTH_SHORT
                    ).show()
                }

                is Resource.Loading -> {
                    binding.loading = true
                }
            }

            if (!response.isLoading) {
                binding.loading = false
            }
        }
    }
}