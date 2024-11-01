package com.example.pokeapp.presentation.diff_callback

import androidx.recyclerview.widget.DiffUtil
import com.example.pokeapp.domain.model.PokemonModel

class PokeDiffCallback: DiffUtil.ItemCallback<PokemonModel>() {
    override fun areItemsTheSame(oldItem: PokemonModel, newItem: PokemonModel): Boolean {
        return oldItem.name == newItem.name
    }

    override fun areContentsTheSame(oldItem: PokemonModel, newItem: PokemonModel): Boolean {
        return oldItem == newItem
    }
}