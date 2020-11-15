package com.javnez.marvel.data.repository.comics

import com.javnez.marvel.core.Result
import com.javnez.marvel.core.Result.Error
import com.javnez.marvel.core.Result.Success
import com.javnez.marvel.data.model.comic.Comic
import com.javnez.marvel.data.repository.comics.datasource.LocalDataSource
import com.javnez.marvel.data.repository.comics.datasource.NetworkDataSource
import javax.inject.Inject

class ComicsRepository @Inject constructor(
    private val networkDataSource: NetworkDataSource,
    private val localDataSource: LocalDataSource
) {

    suspend fun getComics(characterId: Int): Result<List<Comic>> {

        return when (val result = networkDataSource.getComics(characterId)) {
            is Success -> {
                localDataSource.storeComics(result.data)
                result
            }
            is Error -> {
                val localResult = localDataSource.getComics(characterId)
                return if (localResult is Success) localResult else result
            }
        }

    }
}