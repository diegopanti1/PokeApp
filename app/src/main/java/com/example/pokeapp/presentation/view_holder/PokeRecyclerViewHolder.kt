package com.example.pokeapp.presentation.view_holder

import android.view.View
import com.example.pokeapp.databinding.ItemPokemonBinding
import com.example.pokeapp.domain.model.PokemonModel

class PokeRecyclerViewHolder (private val binding: ItemPokemonBinding) : BaseViewHolder<PokemonModel>(binding) {

    var itemClickListener: ((view: View, item: PokemonModel, position: Int)->Unit)? = null

    override fun bind(item: PokemonModel) {
        binding.model = item
        binding.root.setOnClickListener {
            itemClickListener?.invoke(it, item, adapterPosition)
        }
    }
}