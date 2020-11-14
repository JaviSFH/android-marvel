package com.javnez.marvel.data.repositories.datasource

import com.javnez.marvel.core.Result
import com.javnez.marvel.data.model.character.Characters
import javax.inject.Inject

class LocalDataSource @Inject constructor() {

    fun storeCharacters(characters: List<Characters>) {

    }

    fun getCharacters(): Result<List<Characters>> {
        return Result.Success(emptyList())
    }
}