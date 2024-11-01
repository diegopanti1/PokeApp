package com.example.pokeapp.domain.interactor

import com.example.pokeapp.data.repository.PokemonRepository
import com.example.pokeapp.domain.model.PokemonModel
import com.example.pokeapp.domain.model.response.ListResponse
import com.example.pokeapp.domain.resource.Resource
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class DoGetPokemonUseCaseImpl @Inject constructor(private val repository: PokemonRepository) : DoGetPokemonUseCase {
    override suspend fun invoke(offset: Int): Flow<Resource<ListResponse<PokemonModel>>> = repository.getAllPokemon(offset)
}