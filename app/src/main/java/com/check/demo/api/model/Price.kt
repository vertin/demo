package com.check.demo.api.model


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Price(
    @Json(name = "currency")
    val currency: String,
    @Json(name = "value")
    val value: Double
)
