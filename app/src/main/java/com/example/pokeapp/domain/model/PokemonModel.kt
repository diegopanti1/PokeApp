package com.example.pokeapp.domain.model

data class PokemonModel(
    val name: String,
    val url: String
)
{
    fun getFullImageUrl() : String{
        return "https://img.pokemondb.net/artwork/${name}.jpg"
    }
}
