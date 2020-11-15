package com.javnez.marvel.ui.overview

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.javnez.marvel.core.BaseFragment
import com.javnez.marvel.data.model.character.Character
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
                    adapter.submitList(state.characters)
                }
                Error -> {
                    binding.shimmerLayout.hideShimmer()
                    showGenericError()
                }
            }
        })

        viewModel.loadCharacters()
    }

    private fun navigateToDetails(character: Character) {
        val action = OverviewFragmentDirections.nextAction(character)
        findNavController().navigate(action)
    }
}