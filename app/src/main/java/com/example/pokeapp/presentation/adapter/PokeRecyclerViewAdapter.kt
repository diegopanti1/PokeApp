package com.example.pokeapp.presentation.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.RecyclerView
import com.example.pokeapp.databinding.ItemPokemonBinding
import com.example.pokeapp.domain.model.PokemonModel
import com.example.pokeapp.presentation.diff_callback.PokeDiffCallback
import com.example.pokeapp.presentation.view_holder.PokeRecyclerViewHolder

class PokeRecyclerViewAdapter: RecyclerView.Adapter<PokeRecyclerViewHolder>() {
    private val diffCallback = PokeDiffCallback()

    private val differ = AsyncListDiffer(this, diffCallback)

    fun submitList(list: List<PokemonModel>) {
        differ.submitList(list)
    }

    fun addList(list: List<PokemonModel>) {
        val current = differ.currentList.toMutableList()
        current.addAll(list.toMutableList())
        differ.submitList(current)
    }


    var itemClickListener: ((view: View, item: PokemonModel, position: Int)->Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PokeRecyclerViewHolder {
        return PokeRecyclerViewHolder(
            ItemPokemonBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: PokeRecyclerViewHolder, position: Int) {
        holder.itemClickListener = itemClickListener
        holder.bind(differ.currentList[position])
    }

    override fun getItemCount(): Int = differ.currentList.size
}