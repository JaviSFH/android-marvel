package com.javnez.marvel.ui.details.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.javnez.marvel.data.model.comic.Comic
import com.javnez.marvel.databinding.DetailsItemFragmentBinding
import com.javnez.marvel.ui.details.adapters.DetailsAdapter.ComicViewHolder

class DetailsAdapter : ListAdapter<Comic, ComicViewHolder>(ComicDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ComicViewHolder {
        return ComicViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: ComicViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    class ComicViewHolder(private val binding: DetailsItemFragmentBinding) :
        RecyclerView.ViewHolder(binding.root) {

        companion object {
            fun from(viewGroup: ViewGroup): ComicViewHolder {
                val layoutInflater = LayoutInflater.from(viewGroup.context)
                val binding = DetailsItemFragmentBinding.inflate(layoutInflater, viewGroup, false)
                return ComicViewHolder(binding)
            }
        }

        fun bind(comic: Comic) {
            binding.apply {
                model = comic
            }
        }
    }

    class ComicDiffCallback : DiffUtil.ItemCallback<Comic>() {

        override fun areItemsTheSame(oldItem: Comic, newItem: Comic): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Comic, newItem: Comic): Boolean {
            return oldItem.id == newItem.id
        }
    }
}