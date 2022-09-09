package com.check.demo.api.model


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class ProductsResponse(
    @Json(name = "filters")
    val filters: List<String>,
    @Json(name = "header")
    val header: Header,
    @Json(name = "products")
    val productInfo: List<Product>
)
