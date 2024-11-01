package com.example.pokeapp.data.repository

import com.example.pokeapp.domain.model.PokemonDetailModel
import com.example.pokeapp.domain.model.PokemonModel
import com.example.pokeapp.domain.model.response.ListResponse
import com.example.pokeapp.domain.resource.Resource
import kotlinx.coroutines.flow.Flow

interface PokemonRepository {
    suspend fun getAllPokemon(offset: Int): Flow<Resource<ListResponse<PokemonModel>>>
    suspend fun getPokemonByName(name: String): Flow<Resource<PokemonDetailModel>>
}