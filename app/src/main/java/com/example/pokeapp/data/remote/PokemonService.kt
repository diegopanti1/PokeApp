package com.example.pokeapp.data.remote

import com.example.pokeapp.domain.model.PokemonDetailModel
import com.example.pokeapp.domain.model.PokemonModel
import com.example.pokeapp.domain.model.response.ListResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface PokemonService {
    @GET("pokemon")
    suspend fun getAll(@Query("limit") limit: Int? = 10, @Query("offset") offset: Int? = 0): Response<ListResponse<PokemonModel>>

    @GET("pokemon/{name}")
    suspend fun getByName(@Path("name") name: String): Response<PokemonDetailModel>

}