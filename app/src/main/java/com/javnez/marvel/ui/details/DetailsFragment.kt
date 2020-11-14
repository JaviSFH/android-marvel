package com.javnez.marvel.ui.details

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.javnez.marvel.core.BaseFragment
import com.javnez.marvel.data.model.Characters
import com.javnez.marvel.databinding.DetailsFragmentBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailsFragment : BaseFragment() {

    private val viewModel: DetailsViewModel by viewModels()
    private lateinit var binding: DetailsFragmentBinding

    private lateinit var character: Characters

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        setHasOptionsMenu(true)

        binding = DetailsFragmentBinding.inflate(inflater, container, false)
        val safeArgs: DetailsFragmentArgs by navArgs()
        character = safeArgs.character

        return binding.root
    }


    override fun setupUi() {
        //TODO
        activity?.title = character.name
    }

    override fun setupObservers() {
        //TODO
    }
}