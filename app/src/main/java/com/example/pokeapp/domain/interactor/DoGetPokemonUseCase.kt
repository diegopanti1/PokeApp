package com.example.pokeapp.domain.interactor

import com.example.pokeapp.domain.model.PokemonModel
import com.example.pokeapp.domain.model.response.ListResponse
import com.example.pokeapp.domain.resource.Resource
import kotlinx.coroutines.flow.Flow

interface DoGetPokemonUseCase {
    suspend operator fun invoke(offset: Int) : Flow<Resource<ListResponse<PokemonModel>>>
}