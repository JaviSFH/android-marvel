package com.javnez.core_data.repository.comics.datasources

import com.javnez.core_data.core.Failure.ServerError
import com.javnez.core_data.core.Result
import com.javnez.core_data.model.comic.Comic
import com.javnez.core_data.repository.MarvelOperations
import java.io.IOException
import javax.inject.Inject

class NetworkDataSource @Inject constructor(private val operations: MarvelOperations) {

    suspend fun getComics(characterId: Int): Result<List<Comic>> = try {

        val response = operations.getComics(characterId.toString())
        val comics = response.body()?.data?.results

        if (!response.isSuccessful || comics.isNullOrEmpty()) {
            Result.Error(ServerError)
        } else {
            Result.Success(comics)
        }
    } catch (exception: IOException) {
        Result.Error(ServerError)
    }
}