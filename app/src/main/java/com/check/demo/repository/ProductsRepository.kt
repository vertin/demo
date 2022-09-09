package com.check.demo.repository

import com.check.demo.datasource.model.Products
import kotlinx.coroutines.flow.Flow

interface ProductsRepository {

    suspend fun getProducts(): Flow<ResultWrapper<Products, String>>
}
