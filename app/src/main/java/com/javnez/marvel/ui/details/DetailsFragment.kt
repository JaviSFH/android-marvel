package com.javnez.marvel.ui.details

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
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
            recyclerViewComics.adapter = adapter
        }
    }

    override fun setupObservers() {

        viewModel.state.observe(viewLifecycleOwner, Observer { state ->
            when (state) {
                Loading -> {
                    //TODO Show progress
                }
                is Success -> {
                    //TODO Hide progress and set data
                    adapter.submitList(state.comics)
                }
                Error -> {
                    //TODO Hide progress and show error
                }
            }
        })

        viewModel.loadComics(character.id)
    }
}