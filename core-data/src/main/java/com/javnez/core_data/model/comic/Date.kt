package com.javnez.core_data.model.comic

import androidx.annotation.Keep
import com.squareup.moshi.Json

@Keep
data class Date(
    @Json(name = "date")
    val date: String? = "",
    @Json(name = "type")
    val type: String? = ""
)
