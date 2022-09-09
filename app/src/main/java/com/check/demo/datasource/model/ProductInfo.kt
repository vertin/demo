package com.check.demo.datasource.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class ProductInfo(
    val id: Int,
    val name: String,
    val iconUrl: String,
    val price: String,
    val currency: String,
    val date: String,
    val description: String,
    val longDescription: String,
    val rating: Double
) : Parcelable
