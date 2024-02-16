package com.zehcort.data.entities.remote.numbers

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class DateFact(
    @Json(name = "text")
    val text: String? = null,
    @Json(name = "year")
    val year: Int? = null,
    @Json(name = "number")
    val number: Int? = null,
    @Json(name = "found")
    val found: Boolean? = null,
    @Json(name = "type")
    val type: String? = null
)