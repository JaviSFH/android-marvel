package com.javnez.feature_characters.ui.details.adapters

import android.graphics.Bitmap
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.graphics.drawable.toBitmap
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.javnez.core_data.model.comic.Comic
import com.javnez.feature_characters.databinding.DetailsItemFragmentBinding
import com.javnez.feature_characters.ui.details.adapters.DetailsAdapter.ComicViewHolder

class DetailsAdapter : ListAdapter<Comic, ComicViewHolder>(ComicDiffCallback()) {

    private var listener: ((Bitmap) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ComicViewHolder {
        return ComicViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: ComicViewHolder, position: Int) {
        holder.bind(getItem(position), listener)
    }

    fun setOnImageClickListener(listener: (Bitmap) -> Unit) {
        this.listener = listener
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

        fun bind(comic: Comic, listener: ((Bitmap) -> Unit)?) {
            binding.apply {
                model = comic
                imageComic.setOnClickListener { listener?.invoke(imageComic.drawable.toBitmap()) }
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