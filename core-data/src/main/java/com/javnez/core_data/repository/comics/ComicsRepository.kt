package com.javnez.core_data.repository.comics

import com.javnez.core_data.core.Result
import com.javnez.core_data.core.Result.Error
import com.javnez.core_data.core.Result.Success
import com.javnez.core_data.model.comic.Comic
import com.javnez.core_data.repository.comics.datasources.LocalDataSource
import com.javnez.core_data.repository.comics.datasources.NetworkDataSource
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