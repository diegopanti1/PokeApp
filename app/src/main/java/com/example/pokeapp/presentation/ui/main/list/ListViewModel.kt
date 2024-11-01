package com.example.pokeapp.presentation.ui.main.list

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.pokeapp.R
import com.example.pokeapp.di.ResourcesProvider
import com.example.pokeapp.domain.interactor.DoGetPokemonUseCase
import com.example.pokeapp.domain.model.PokemonModel
import com.example.pokeapp.domain.model.response.ListResponse
import com.example.pokeapp.domain.resource.Resource
import com.example.pokeapp.platform.NetworkHandler
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ListViewModel  @Inject constructor(
    private val doGetPokemonUseCase: DoGetPokemonUseCase,
    private val networkHandler: NetworkHandler,
    private val resourcesProvider: ResourcesProvider
): ViewModel() {
    private val _pokemons = MutableLiveData<Resource<ListResponse<PokemonModel>>>()
    val pokemons: LiveData<Resource<ListResponse<PokemonModel>>> get() = _pokemons

    private val _pokemonsOffset = MutableLiveData<Resource<ListResponse<PokemonModel>>>()
    val pokemonsOffset: LiveData<Resource<ListResponse<PokemonModel>>> get() = _pokemonsOffset

    var mOffSet: Int = 0
    fun getPokemons(offset: Int? = 0) {
        mOffSet += (offset ?: 0)
        if (networkHandler.isConnected!!) {
            _pokemons.postValue(Resource.Loading)
            viewModelScope.launch {
                doGetPokemonUseCase(mOffSet).collect { result ->
                    when (result) {
                        is Resource.Success -> if(offset != null && offset > 0) _pokemonsOffset.postValue(result) else _pokemons.postValue(result)
                        is Resource.Failure -> if(offset != null && offset > 0) _pokemonsOffset.postValue(result) else _pokemons.postValue(result)
                        else -> if(offset != null && offset > 0) _pokemonsOffset.postValue(Resource.Loading) else _pokemons.postValue(Resource.Loading)
                    }
                }
            }
        } else {
            _pokemons.postValue(
                Resource.Failure(
                    true,
                    404,
                    resourcesProvider.getString(R.string.error_network)
                )
            )
        }
    }
}