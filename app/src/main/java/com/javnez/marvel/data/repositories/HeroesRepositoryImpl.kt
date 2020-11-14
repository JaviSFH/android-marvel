package com.javnez.marvel.data.repositories

import com.javnez.marvel.data.repositories.datasource.LocalDataSource
import com.javnez.marvel.data.repositories.datasource.NetworkDataSource
import com.javnez.marvel.domain.repositories.HeroesRepository

class HeroesRepositoryImpl(
    private val networkDataSource: NetworkDataSource,
    private val localDataSource: LocalDataSource
) : HeroesRepository {
    override suspend fun getCharacters() {
        TODO("Not yet implemented")
    }
}