package com.javnez.marvel.ui.overview

import com.javnez.marvel.data.model.Characters

sealed class OverviewState {
    object Loading : OverviewState()
    class Success(val characters: List<Characters>) : OverviewState()
    object Error : OverviewState()
}