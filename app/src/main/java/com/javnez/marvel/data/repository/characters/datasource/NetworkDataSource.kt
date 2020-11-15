package com.javnez.marvel.data.repository.characters.datasource

import com.javnez.marvel.core.Failure.ServerError
import com.javnez.marvel.core.Result
import com.javnez.marvel.data.model.character.Character
import com.javnez.marvel.data.repository.MarvelOperations
import java.io.IOException
import javax.inject.Inject

class NetworkDataSource @Inject constructor(private val operations: MarvelOperations) {

    suspend fun getCharacters(): Result<List<Character>> = try {

        val response = operations.getCharacters()

        val characters = response.body()?.data?.results
        if (!response.isSuccessful || characters.isNullOrEmpty()) {
            Result.Error(ServerError)
        } else {
            Result.Success(characters)
        }
    } catch (exception: IOException) {
        Result.Error(ServerError)
    }
}