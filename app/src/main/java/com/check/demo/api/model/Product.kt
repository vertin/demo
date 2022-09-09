package com.check.demo.api.model


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Product(
    @Json(name = "available")
    val available: Boolean,
    @Json(name = "color")
    val color: String,
    @Json(name = "colorCode")
    val colorCode: String,
    @Json(name = "description")
    val description: String,
    @Json(name = "id")
    val id: Int,
    @Json(name = "imageURL")
    val imageURL: String,
    @Json(name = "longDescription")
    val longDescription: String,
    @Json(name = "name")
    val name: String,
    @Json(name = "price")
    val price: Price,
    @Json(name = "rating")
    val rating: Double,
    @Json(name = "releaseDate")
    val releaseDate: Long,
    @Json(name = "type")
    val type: String
)
