package com.javnez.feature_characters.ui.overview

import com.javnez.core_data.model.character.Character

sealed class OverviewState {
    object Loading : OverviewState()
    class Success(val characters: List<Character>) : OverviewState()
    object Error : OverviewState()
}