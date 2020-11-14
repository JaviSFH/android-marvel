package com.javnez.marvel.data.repositorie.comics

import com.javnez.marvel.core.Failure.NetworkConnection
import com.javnez.marvel.core.NetworkUtils
import com.javnez.marvel.core.Result
import com.javnez.marvel.core.Result.Error
import com.javnez.marvel.core.Result.Success
import com.javnez.marvel.data.model.comic.Comic
import com.javnez.marvel.data.repositorie.comics.datasource.LocalDataSource
import com.javnez.marvel.data.repositorie.comics.datasource.NetworkDataSource
import javax.inject.Inject

class ComicsRepository @Inject constructor(
    private val networkUtils: NetworkUtils,
    private val networkDataSource: NetworkDataSource,
    private val localDataSource: LocalDataSource
) {

    suspend fun getComics(characterId: Int): Result<List<Comic>> {
        if (!networkUtils.isNetworkAvailable()) return Error(NetworkConnection)

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