package com.check.demo.datasource

import com.check.demo.datasource.model.Products

interface ProductDataSource {

    suspend fun getProductList(): DataResultWrapper<Products, String>
}
