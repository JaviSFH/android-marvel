package com.javnez.core_data.model.character

import androidx.annotation.Keep
import com.javnez.core_data.model.Thumbnail
import com.squareup.moshi.Json
import java.io.Serializable

@Keep
data class Character(
    @Json(name = "id")
    val id: Int?,
    @Json(name = "name")
    val name: String?,
    @Json(name = "description")
    val description: String?,
    @Json(name = "thumbnail")
    val thumbnail: Thumbnail?,
) : Serializable

