package com.check.demo.ui.navigation.navtype

import android.os.Build
import android.os.Bundle
import androidx.navigation.NavType
import com.check.demo.datasource.model.ProductInfo
import com.squareup.moshi.Moshi

class ProductNavType(private val moshi: Moshi) : NavType<ProductInfo>(isNullableAllowed = false) {

    override fun get(bundle: Bundle, key: String): ProductInfo? {
        return if (Build.VERSION.SDK_INT >= 33) {
            bundle.getParcelable(key, ProductInfo::class.java)
        } else {
            bundle.getParcelable(key)
        }
    }

    override fun parseValue(value: String): ProductInfo {
        return moshi.adapter(ProductInfo::class.java).fromJson(value)
            ?: throw IllegalStateException()
    }

    override fun put(bundle: Bundle, key: String, value: ProductInfo) {
        bundle.putParcelable(key, value)
    }
}
