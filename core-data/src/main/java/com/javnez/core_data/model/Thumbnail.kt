package com.javnez.core_data.model

import androidx.annotation.Keep
import com.squareup.moshi.Json
import java.io.Serializable

@Keep
data class Thumbnail(
    @Json(name = "path")
    val path: String?,
    @Json(name = "extension")
    val extension: String?
) : Serializable
