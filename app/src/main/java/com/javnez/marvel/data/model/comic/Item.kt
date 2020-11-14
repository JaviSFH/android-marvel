package com.javnez.marvel.data.model.comic

import androidx.annotation.Keep
import com.squareup.moshi.Json

@Keep
data class Item(
    @Json(name = "name")
    val name: String? = "",
    @Json(name = "resourceURI")
    val resourceURI: String? = ""
)
