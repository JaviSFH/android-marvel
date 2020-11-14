package com.javnez.marvel.data.model.comic

import androidx.annotation.Keep
import com.squareup.moshi.Json

@Keep
data class ComicData(
    @Json(name = "count")
    val count: Int? = 0,
    @Json(name = "limit")
    val limit: Int? = 0,
    @Json(name = "offset")
    val offset: Int? = 0,
    @Json(name = "results")
    val results: List<Comic>? = emptyList(),
    @Json(name = "total")
    val total: Int? = 0
)
