package com.javnez.marvel.data.repositories.datasource

import com.javnez.marvel.core.Failure.ServerError
import com.javnez.marvel.core.Result
import com.javnez.marvel.data.model.character.Characters
import javax.inject.Inject

class NetworkDataSource @Inject constructor(private val operations: MarvelOperations) {

    suspend fun getCharacters(): Result<List<Characters>> {

        val response = operations.getCharacters()
        val characters = response.body()?.data?.results

        return if (!response.isSuccessful || characters.isNullOrEmpty()) {
            Result.Error(ServerError)
        } else {
            Result.Success(characters)
        }
    }
}