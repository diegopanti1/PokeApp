package com.example.pokeapp.domain.model

import com.google.gson.annotations.SerializedName

data class PokemonDetailModel(
    @SerializedName("abilities") var abilities: ArrayList<Abilities> = arrayListOf(),
    @SerializedName("base_experience") var baseExperience: String? = "0",
    @SerializedName("cries") var cries: Cries? = Cries(),
    @SerializedName("forms") var forms: ArrayList<Forms> = arrayListOf(),
    @SerializedName("game_indices") var gameIndices: ArrayList<GameIndices> = arrayListOf(),
    @SerializedName("height") var height: Int? = null,
    @SerializedName("held_items") var heldItems: ArrayList<String> = arrayListOf(),
    @SerializedName("id") var id: Int? = null,
    @SerializedName("is_default") var isDefault: Boolean? = null,
    @SerializedName("location_area_encounters") var locationAreaEncounters: String? = null,
    @SerializedName("name") var name: String? = null,
    @SerializedName("order") var order: Int? = null,
    @SerializedName("past_abilities") var pastAbilities: ArrayList<String> = arrayListOf(),
    @SerializedName("past_types") var pastTypes: ArrayList<String> = arrayListOf(),
    @SerializedName("sprites") var sprites: Sprites? = Sprites(),
    @SerializedName("weight") var weight: String? = "0"

) {
    fun getFullImageUrl(): String {
        return "https://img.pokemondb.net/artwork/${name}.jpg"
    }
}

data class Sprites(
    val font_default: String? = "",
    val font_shiny: String? = "",
)

data class PokeType(
    val slot: Int,
    val type: Type
)

data class Type(
    val name: String,
    val url: String
)

data class Ability(

    @SerializedName("name") var name: String? = null,
    @SerializedName("url") var url: String? = null

)

data class Abilities(

    @SerializedName("ability") var ability: Ability? = Ability(),
    @SerializedName("is_hidden") var isHidden: Boolean? = null,
    @SerializedName("slot") var slot: Int? = null

)

data class Cries(

    @SerializedName("latest") var latest: String? = null,
    @SerializedName("legacy") var legacy: String? = null

)


data class Forms(

    @SerializedName("name") var name: String? = null,
    @SerializedName("url") var url: String? = null

)

data class Version(

    @SerializedName("name") var name: String? = null,
    @SerializedName("url") var url: String? = null

)

data class GameIndices(

    @SerializedName("game_index") var gameIndex: Int? = null,
    @SerializedName("version") var version: Version? = Version()

)

data class Move(

    @SerializedName("name") var name: String? = null,
    @SerializedName("url") var url: String? = null

)

data class MoveLearnMethod(

    @SerializedName("name") var name: String? = null,
    @SerializedName("url") var url: String? = null

)
