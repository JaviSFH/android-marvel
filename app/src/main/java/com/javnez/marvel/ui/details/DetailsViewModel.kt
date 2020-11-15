package com.javnez.marvel.ui.details

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.javnez.marvel.core.Result.Error
import com.javnez.marvel.core.Result.Success
import com.javnez.marvel.data.model.comic.Comic
import com.javnez.marvel.domain.usecase.GetComicsUseCase
import com.javnez.marvel.domain.usecase.GetComicsUseCase.Params

class DetailsViewModel @ViewModelInject constructor(private val getComicsUseCase: GetComicsUseCase) : ViewModel() {

    private val _state: MutableLiveData<DetailsState> = MutableLiveData()
    val state: LiveData<DetailsState> get() = _state

    fun loadComics(characterId: Int?) {
        if (_state.value != null) return
        if (characterId == null) return handleError()

        _state.value = DetailsState.Loading

        getComicsUseCase(viewModelScope, Params(characterId)) {
            when (it) {
                is Success -> handleSuccess(it.data)
                is Error -> handleError()
            }
        }
    }

    private fun handleSuccess(comics: List<Comic>) {
        _state.value = DetailsState.Success(comics)
    }

    private fun handleError() {
        _state.value = DetailsState.Error
    }
}