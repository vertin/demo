package com.check.demo.api.model

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class ProductsError(val error: String?)
