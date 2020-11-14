package com.javnez.marvel.ui.overview.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.javnez.marvel.data.model.character.Characters
import com.javnez.marvel.databinding.OverviewItemFragmentBinding
import com.javnez.marvel.ui.overview.adapters.OverviewAdapter.CharacterViewHolder

class OverviewAdapter : ListAdapter<Characters, CharacterViewHolder>(CharactersDiffCallback()) {

    private var listener: ((Characters) -> Unit?)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CharacterViewHolder {
        return CharacterViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: CharacterViewHolder, position: Int) {
        holder.bind(getItem(position), listener)
    }

    fun setOnItemClickListener(listener: (Characters) -> Unit) {
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

        fun bind(character: Characters, listener: ((Characters) -> Unit?)?) {
            binding.apply {
                model = character
                cardView.setOnClickListener { listener?.invoke(character) }
            }
        }
    }

    class CharactersDiffCallback : DiffUtil.ItemCallback<Characters>() {

        override fun areItemsTheSame(oldItem: Characters, newItem: Characters): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Characters, newItem: Characters): Boolean {
            return oldItem.id == newItem.id
        }
    }
}