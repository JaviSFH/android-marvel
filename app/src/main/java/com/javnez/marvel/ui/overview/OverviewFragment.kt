package com.javnez.marvel.ui.overview

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.javnez.marvel.core.BaseFragment
import com.javnez.marvel.data.model.character.Characters
import com.javnez.marvel.databinding.OverviewFragmentBinding
import com.javnez.marvel.ui.overview.OverviewState.Error
import com.javnez.marvel.ui.overview.OverviewState.Loading
import com.javnez.marvel.ui.overview.OverviewState.Success
import com.javnez.marvel.ui.overview.adapters.OverviewAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class OverviewFragment : BaseFragment() {

    private val viewModel: OverviewViewModel by viewModels()
    private lateinit var binding: OverviewFragmentBinding
    private val adapter = OverviewAdapter()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = OverviewFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun setupUi() {
        binding.recyclerViewCharacters.adapter = adapter
        adapter.setOnItemClickListener { navigateToDetails(it) }
    }

    override fun setupObservers() {

        viewModel.state.observe(viewLifecycleOwner, Observer { state ->
            when (state) {
                Loading -> {
                    //TODO Show progress
                }
                is Success -> {
                    //TODO Hide progress and set adapter
                    adapter.submitList(state.characters)
                }
                Error -> {
                    //TODO Hide progress and show error
                }
            }
        })

        viewModel.loadCharacters()
    }

    private fun navigateToDetails(character: Characters) {
        val action = OverviewFragmentDirections.nextAction(character)
        findNavController().navigate(action)
    }
}