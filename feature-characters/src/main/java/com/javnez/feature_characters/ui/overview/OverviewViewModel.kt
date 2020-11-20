package com.javnez.feature_characters.ui.overview

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.javnez.core_data.core.Failure
import com.javnez.core_data.core.Result.Error
import com.javnez.core_data.core.Result.Success
import com.javnez.feature_characters.core.UseCase.None
import com.javnez.core_data.model.character.Character
import com.javnez.feature_characters.domain.usecase.GetCharactersUseCase

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