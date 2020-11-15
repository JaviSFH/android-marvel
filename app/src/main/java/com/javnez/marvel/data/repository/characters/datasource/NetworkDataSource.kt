package com.javnez.marvel.data.repository.characters.datasource

import com.javnez.marvel.core.Failure.ServerError
import com.javnez.marvel.core.Result
import com.javnez.marvel.data.model.character.Character
import com.javnez.marvel.data.repository.MarvelOperations
import javax.inject.Inject

class NetworkDataSource @Inject constructor(private val operations: MarvelOperations) {

    suspend fun getCharacters(): Result<List<Character>> {

        val response = operations.getCharacters()
        val characters = response.body()?.data?.results

        return if (!response.isSuccessful || characters.isNullOrEmpty()) {
            Result.Error(ServerError)
        } else {
            Result.Success(characters)
        }
    }
}