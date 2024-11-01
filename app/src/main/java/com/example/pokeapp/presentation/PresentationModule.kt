package com.example.pokeapp.presentation

import com.example.pokeapp.domain.interactor.DoGetPokemonDetailUseCase
import com.example.pokeapp.domain.interactor.DoGetPokemonDetailUseCaseImpl
import com.example.pokeapp.domain.interactor.DoGetPokemonUseCase
import com.example.pokeapp.domain.interactor.DoGetPokemonUseCaseImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
interface PresentationModule {
    @Binds
    fun bindDoGetPokemonUseCase(doGetPokemonUseCase: DoGetPokemonUseCaseImpl): DoGetPokemonUseCase

    @Binds
    fun bindDoGetPokemonDetailUseCase(doGetPokemonDetailUseCase: DoGetPokemonDetailUseCaseImpl): DoGetPokemonDetailUseCase
}