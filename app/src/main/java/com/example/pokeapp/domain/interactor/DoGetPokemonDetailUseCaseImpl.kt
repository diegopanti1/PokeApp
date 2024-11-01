package com.example.pokeapp.domain.interactor

import com.example.pokeapp.data.repository.PokemonRepository
import com.example.pokeapp.domain.model.PokemonDetailModel
import com.example.pokeapp.domain.resource.Resource
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class DoGetPokemonDetailUseCaseImpl @Inject constructor(private val repository: PokemonRepository) : DoGetPokemonDetailUseCase {
    override suspend fun invoke(name: String): Flow<Resource<PokemonDetailModel>> = repository.getPokemonByName(name)
}