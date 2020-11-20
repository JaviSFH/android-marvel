package com.javnez.core_data.repository.characters.datasource

import com.javnez.core_data.core.Failure.ServerError
import com.javnez.core_data.core.Result
import com.javnez.core_data.model.character.Character
import javax.inject.Inject

class LocalDataSource @Inject constructor() {

    fun storeCharacters(characters: List<Character>) {
        //TODO Implement local persistence
    }

    fun getCharacters(): Result<List<Character>> {
        return Result.Error(ServerError)
    }
}