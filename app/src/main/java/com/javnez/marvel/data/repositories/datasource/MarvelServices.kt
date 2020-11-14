package com.javnez.marvel.data.repositories.datasource

import com.javnez.marvel.data.model.character.CharacterResult
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface MarvelOperations {

    companion object {
        const val BASE_URL = "https://gateway.marvel.com/"

        const val ENDPOINT_CHARACTERS = "/v1/public/characters"
    }

    @GET("$BASE_URL$ENDPOINT_CHARACTERS")
    suspend fun getCharacters(
        @Query("limit") limit: Int = 50,
        @Query("offset") offset: Int = 0
    ): Response<CharacterResult>
}
