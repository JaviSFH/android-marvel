package com.javnez.core_data.repository.characters

import com.javnez.core_data.core.Result
import com.javnez.core_data.core.Result.Error
import com.javnez.core_data.core.Result.Success
import com.javnez.core_data.model.character.Character
import com.javnez.core_data.repository.characters.datasource.LocalDataSource
import com.javnez.core_data.repository.characters.datasource.NetworkDataSource
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