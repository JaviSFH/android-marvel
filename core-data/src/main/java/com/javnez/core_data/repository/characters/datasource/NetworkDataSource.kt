package com.javnez.core_data.repository.characters.datasource

import com.javnez.core_data.core.Failure.ServerError
import com.javnez.core_data.core.Result
import com.javnez.core_data.model.character.Character
import com.javnez.core_data.repository.MarvelOperations
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