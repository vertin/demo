package com.check.demo.api.model


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Header(
    @Json(name = "headerDescription")
    val headerDescription: String,
    @Json(name = "headerTitle")
    val headerTitle: String
)
