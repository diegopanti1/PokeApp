package com.example.pokeapp.presentation.ui.main.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.pokeapp.R
import com.example.pokeapp.di.ResourcesProvider
import com.example.pokeapp.domain.interactor.DoGetPokemonDetailUseCase
import com.example.pokeapp.domain.model.PokemonDetailModel
import com.example.pokeapp.domain.resource.Resource
import com.example.pokeapp.platform.NetworkHandler
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailViewModel  @Inject constructor(
    private val doGetPokemonDetailUseCase: DoGetPokemonDetailUseCase,
    private val networkHandler: NetworkHandler,
    private val resourcesProvider: ResourcesProvider
) : ViewModel() {
    private val _pokemon = MutableLiveData<Resource<PokemonDetailModel>>()
    val pokemon: LiveData<Resource<PokemonDetailModel>> get() = _pokemon

    fun getPokemon(name: String) {
        if (networkHandler.isConnected!!) {
            _pokemon.postValue(Resource.Loading)
            viewModelScope.launch {
                doGetPokemonDetailUseCase(name).collect { result ->
                    when (result) {
                        is Resource.Success -> _pokemon.postValue(result)
                        is Resource.Failure -> _pokemon.postValue(result)
                        else -> _pokemon.postValue(Resource.Loading)
                    }
                }
            }
        } else {
            _pokemon.postValue(
                Resource.Failure(
                    true,
                    404,
                    resourcesProvider.getString(R.string.errorDetail)
                )
            )
        }
    }
}