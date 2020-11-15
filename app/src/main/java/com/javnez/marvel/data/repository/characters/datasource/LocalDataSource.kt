package com.javnez.marvel.data.repository.characters.datasource

import com.javnez.marvel.core.Result
import com.javnez.marvel.data.model.character.Character
import javax.inject.Inject

class LocalDataSource @Inject constructor() {

    fun storeCharacters(characters: List<Character>) {

    }

    fun getCharacters(): Result<List<Character>> {
        return Result.Success(emptyList())
    }
}