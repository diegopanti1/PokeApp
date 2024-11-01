package com.example.pokeapp.domain

import com.example.pokeapp.data.repository.PokemonRepository
import com.example.pokeapp.data.repository.PokemonRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface DomainModule {
    @Binds
    fun bindPokemonRepository(pokemonRepository: PokemonRepositoryImpl) : PokemonRepository
}