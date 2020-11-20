package com.javnez.marvel.ui.overview.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.javnez.core_data.model.character.Character
import com.javnez.marvel.databinding.OverviewItemFragmentBinding
import com.javnez.marvel.ui.overview.adapters.OverviewAdapter.CharacterViewHolder

class OverviewAdapter : ListAdapter<Character, CharacterViewHolder>(CharactersDiffCallback()) {

    private var listener: ((Character) -> Unit?)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CharacterViewHolder {
        return CharacterViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: CharacterViewHolder, position: Int) {
        holder.bind(getItem(position), listener)
    }

    fun setOnItemClickListener(listener: (Character) -> Unit) {
        this.listener = listener
    }

    class CharacterViewHolder(private val binding: OverviewItemFragmentBinding) :
        RecyclerView.ViewHolder(binding.root) {

        companion object {
            fun from(viewGroup: ViewGroup): CharacterViewHolder {
                val layoutInflater = LayoutInflater.from(viewGroup.context)
                val binding = OverviewItemFragmentBinding.inflate(layoutInflater, viewGroup, false)
                return CharacterViewHolder(binding)
            }
        }

        fun bind(character: Character, listener: ((Character) -> Unit?)?) {
            binding.apply {
                model = character
                cardView.setOnClickListener { listener?.invoke(character) }
            }
        }
    }

    class CharactersDiffCallback : DiffUtil.ItemCallback<Character>() {

        override fun areItemsTheSame(oldItem: Character, newItem: Character): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Character, newItem: Character): Boolean {
            return oldItem.id == newItem.id
        }
    }
}