package com.javnez.marvel.data.model.comic

import androidx.annotation.Keep
import com.javnez.marvel.data.model.Thumbnail
import com.javnez.marvel.data.model.character.Characters
import com.squareup.moshi.Json

@Keep
data class Comic(
    @Json(name = "characters")
    val characters: Characters?,
    @Json(name = "collectedIssues")
    val collectedIssues: List<Any>? = listOf(),
    @Json(name = "collections")
    val collections: List<Any>? = listOf(),
    @Json(name = "creators")
    val creators: Creators? = Creators(),
    @Json(name = "dates")
    val dates: List<Date>? = listOf(),
    @Json(name = "description")
    val description: String? = "",
    @Json(name = "format")
    val format: String? = "",
    @Json(name = "id")
    val id: Int? = 0,
    @Json(name = "isbn")
    val isbn: String? = "",
    @Json(name = "issueNumber")
    val issueNumber: Int? = 0,
    @Json(name = "modified")
    val modified: String? = "",
    @Json(name = "pageCount")
    val pageCount: Int? = 0,
    @Json(name = "resourceURI")
    val resourceURI: String? = "",
    @Json(name = "thumbnail")
    val thumbnail: Thumbnail?,
    @Json(name = "title")
    val title: String? = "",
)
