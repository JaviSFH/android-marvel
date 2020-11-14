package com.javnez.marvel.ui.details

import com.javnez.marvel.data.model.comic.Comic

sealed class DetailsState {
    object Loading : DetailsState()
    class Success(val comics: List<Comic>) : DetailsState()
    object Error : DetailsState()
}