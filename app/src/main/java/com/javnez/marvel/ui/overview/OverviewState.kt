package com.javnez.marvel.ui.overview

import com.javnez.marvel.data.model.character.Character

sealed class OverviewState {
    object Loading : OverviewState()
    class Success(val characters: List<Character>) : OverviewState()
    object Error : OverviewState()
}