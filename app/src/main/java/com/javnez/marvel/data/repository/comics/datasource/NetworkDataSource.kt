package com.javnez.marvel.data.repository.comics.datasource

import com.javnez.marvel.core.Failure.ServerError
import com.javnez.marvel.core.Result
import com.javnez.marvel.data.model.comic.Comic
import com.javnez.marvel.data.repository.MarvelOperations
import javax.inject.Inject

class NetworkDataSource @Inject constructor(private val operations: MarvelOperations) {

    suspend fun getComics(characterId: Int): Result<List<Comic>> {

        val response = operations.getComics(characterId.toString())
        val comics = response.body()?.data?.results

        return if (!response.isSuccessful || comics.isNullOrEmpty()) {
            Result.Error(ServerError)
        } else {
            Result.Success(comics)
        }
    }
}