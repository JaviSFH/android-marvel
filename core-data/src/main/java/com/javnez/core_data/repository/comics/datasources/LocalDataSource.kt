package com.javnez.core_data.repository.comics.datasources

import com.javnez.core_data.core.Failure.ServerError
import com.javnez.core_data.core.Result
import com.javnez.core_data.model.comic.Comic
import javax.inject.Inject

class LocalDataSource @Inject constructor() {

    fun storeComics(comics: List<Comic>) {
        //TODO Implement local persistence
    }

    fun getComics(characterId: Int): Result<List<Comic>> {
        return Result.Error(ServerError)
    }
}