package com.javnez.marvel.data.repository.comics.datasource

import com.javnez.marvel.core.Result
import com.javnez.marvel.data.model.comic.Comic
import javax.inject.Inject

class LocalDataSource @Inject constructor() {

    fun storeComics(comics: List<Comic>) {}

    fun getComics(characterId: Int): Result<List<Comic>> {
        return Result.Success(emptyList())
    }
}