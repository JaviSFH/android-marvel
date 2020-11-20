package com.javnez.core_data.model.comic

import androidx.annotation.Keep
import com.squareup.moshi.Json

@Keep
data class Creators(
    @Json(name = "available")
    val available: Int? = 0,
    @Json(name = "collectionURI")
    val collectionURI: String? = "",
    @Json(name = "items")
    val items: List<Item>? = listOf(),
    @Json(name = "returned")
    val returned: Int? = 0
)
