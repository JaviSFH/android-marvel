package com.javnez.feature_characters.core

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.google.android.material.snackbar.Snackbar
import com.javnez.feature_characters.R

abstract class BaseFragment : Fragment() {

    abstract fun setupUi()
    abstract fun setupObservers()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupUi()
        setupObservers()
    }

    fun showGenericError() {
        Snackbar.make(this.requireView(), R.string.generic_error, Snackbar.LENGTH_INDEFINITE).show()
    }
}