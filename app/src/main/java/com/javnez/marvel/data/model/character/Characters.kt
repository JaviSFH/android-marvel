package com.javnez.marvel.data.model.character

import androidx.annotation.Keep
import com.javnez.marvel.data.model.Thumbnail
import com.squareup.moshi.Json
import java.io.Serializable

@Keep
data class Characters(
    @Json(name = "id")
    val id: Int?,
    @Json(name = "name")
    val name: String?,
    @Json(name = "description")
    val description: String?,
    @Json(name = "thumbnail")
    val thumbnail: Thumbnail?,
) : Serializable

