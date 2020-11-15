package com.javnez.marvel.data.repository.characters

import com.javnez.marvel.core.Result
import com.javnez.marvel.core.Result.Error
import com.javnez.marvel.core.Result.Success
import com.javnez.marvel.data.model.character.Character
import com.javnez.marvel.data.repository.characters.datasource.LocalDataSource
import com.javnez.marvel.data.repository.characters.datasource.NetworkDataSource
import javax.inject.Inject

class CharactersRepository @Inject constructor(
    private val networkDataSource: NetworkDataSource,
    private val localDataSource: LocalDataSource
) {

    suspend fun getCharacters(): Result<List<Character>> {
        return when (val result = networkDataSource.getCharacters()) {
            is Success -> {
                localDataSource.storeCharacters(result.data)
                result
            }
            is Error -> {
                val localResult = localDataSource.getCharacters()
                return if (localResult is Success) localResult else result
            }
        }

    }
}