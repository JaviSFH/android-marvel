package com.javnez.marvel.ui.details

import android.app.Dialog
import android.graphics.Bitmap
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import android.view.ViewGroup
import android.view.Window
import android.widget.ImageView
import android.widget.RelativeLayout
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.javnez.marvel.core.BaseFragment
import com.javnez.marvel.data.model.character.Character
import com.javnez.marvel.databinding.DetailsFragmentBinding
import com.javnez.marvel.ui.details.DetailsState.Error
import com.javnez.marvel.ui.details.DetailsState.Loading
import com.javnez.marvel.ui.details.DetailsState.Success
import com.javnez.marvel.ui.details.adapters.DetailsAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailsFragment : BaseFragment() {

    private val viewModel: DetailsViewModel by viewModels()
    private lateinit var binding: DetailsFragmentBinding
    private lateinit var character: Character
    private val adapter = DetailsAdapter()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DetailsFragmentBinding.inflate(inflater, container, false)
        val safeArgs: DetailsFragmentArgs by navArgs()
        character = safeArgs.character
        return binding.root
    }

    override fun setupUi() {
        binding.apply {
            characterModel = character
            toolbar.setNavigationOnClickListener { findNavController().navigateUp() }
            adapter.setOnImageClickListener { showImage(it) }
            recyclerViewComics.adapter = adapter
        }
    }

    override fun setupObservers() {

        viewModel.state.observe(viewLifecycleOwner, { state ->
            when (state) {
                Loading -> {
                    binding.shimmerLayout.visibility = VISIBLE
                }
                is Success -> {
                    binding.shimmerLayout.apply {
                        stopShimmer()
                        visibility = GONE
                    }
                    adapter.submitList(state.comics)
                }
                Error -> {
                    binding.shimmerLayout.hideShimmer()
                    showGenericError()
                }
            }
        })

        viewModel.loadComics(character.id)
    }

    private fun showImage(bitmap: Bitmap) {

        val imageView = ImageView(requireContext()).apply { setImageBitmap(bitmap) }
        Dialog(requireContext()).apply {
            requestWindowFeature(Window.FEATURE_NO_TITLE)
            window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
            addContentView(imageView, RelativeLayout.LayoutParams(1000, 1200))
            setCanceledOnTouchOutside(true)
        }.show()
    }
}