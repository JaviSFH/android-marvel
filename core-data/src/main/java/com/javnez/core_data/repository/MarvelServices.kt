package com.javnez.core_data.repository

import com.javnez.core_data.model.character.CharacterResult
import com.javnez.core_data.model.comic.ComicResult
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MarvelOperations {

    companion object {
        const val BASE_URL = "https://gateway.marvel.com/"

        const val ENDPOINT_CHARACTERS = "v1/public/characters"
        const val PATH_PARAM_CHARACTER_ID = "characterId"
    }

    @GET("$BASE_URL$ENDPOINT_CHARACTERS")
    suspend fun getCharacters(
        @Query("limit") limit: Int = 50,
        @Query("offset") offset: Int = 0
    ): Response<CharacterResult>

    @GET("$BASE_URL$ENDPOINT_CHARACTERS/{$PATH_PARAM_CHARACTER_ID}/comics")
    suspend fun getComics(@Path(PATH_PARAM_CHARACTER_ID) characterId: String): Response<ComicResult>
}
