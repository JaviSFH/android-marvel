package com.javnez.marvel.ui.overview

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.javnez.marvel.core.Failure
import com.javnez.marvel.core.Result.Error
import com.javnez.marvel.core.Result.Success
import com.javnez.marvel.core.UseCase.None
import com.javnez.marvel.data.model.character.Character
import com.javnez.marvel.domain.usecase.GetCharactersUseCase

class OverviewViewModel @ViewModelInject constructor(private val getCharactersUseCase: GetCharactersUseCase) : ViewModel() {

    private val _state: MutableLiveData<OverviewState> = MutableLiveData()
    val state: LiveData<OverviewState> get() = _state

    fun loadCharacters() {
        if (_state.value != null) return

        _state.value = OverviewState.Loading
        getCharactersUseCase(viewModelScope, None()) {
            when (it) {
                is Success -> handleSuccess(it.data)
                is Error -> handleError(it.failure)
            }
        }
    }

    private fun handleSuccess(characters: List<Character>) {
        _state.value = OverviewState.Success(characters)
    }

    private fun handleError(failure: Failure) {
        _state.value = OverviewState.Error
    }
}