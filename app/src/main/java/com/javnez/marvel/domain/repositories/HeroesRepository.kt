package com.javnez.marvel.domain.repositories

interface HeroesRepository {

    suspend fun getCharacters()
}