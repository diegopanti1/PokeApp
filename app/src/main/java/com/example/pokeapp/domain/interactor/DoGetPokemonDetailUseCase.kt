package com.example.pokeapp.domain.interactor

import com.example.pokeapp.domain.model.PokemonDetailModel
import com.example.pokeapp.domain.resource.Resource
import kotlinx.coroutines.flow.Flow

interface DoGetPokemonDetailUseCase {
    suspend operator fun invoke(name: String) : Flow<Resource<PokemonDetailModel>>
}