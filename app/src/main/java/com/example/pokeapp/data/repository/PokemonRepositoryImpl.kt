package com.example.pokeapp.data.repository

import com.example.pokeapp.R
import com.example.pokeapp.data.remote.PokemonService
import com.example.pokeapp.di.ResourcesProvider
import com.example.pokeapp.domain.model.PokemonDetailModel
import com.example.pokeapp.domain.model.PokemonModel
import com.example.pokeapp.domain.model.response.ListResponse
import com.example.pokeapp.domain.resource.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class PokemonRepositoryImpl @Inject constructor(private val api: PokemonService, private val resourcesProvider: ResourcesProvider) : PokemonRepository, BaseRepository() {
    override suspend fun getAllPokemon(offset: Int): Flow<Resource<ListResponse<PokemonModel>>> = flow {
        emit(Resource.Loading)
        val response = getResponse(
            request = { api.getAll(offset = offset) },
            defaultErrorMessage = resourcesProvider.getString(R.string.error)
        )
        emit(response)
    }

    override suspend fun getPokemonByName(name: String): Flow<Resource<PokemonDetailModel>> = flow {
        emit(Resource.Loading)
        val response = getResponse(
            request = { api.getByName(name) },
            defaultErrorMessage = resourcesProvider.getString(R.string.errorDetail)
        )
        emit(response)
    }
}