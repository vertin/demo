package com.check.demo.repository

import com.check.demo.datasource.DataResultWrapper
import com.check.demo.datasource.ProductDataSource
import com.check.demo.datasource.model.Products
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class ProductRepositoryImpl(private val ds: ProductDataSource) : ProductsRepository {

    override suspend fun getProducts(): Flow<ResultWrapper<Products, String>> {
        return flow {
            emit(ResultWrapper.Loading())
            when (val result = ds.getProductList()) {
                is DataResultWrapper.Error -> emit(ResultWrapper.Error("Err"))
                is DataResultWrapper.Success -> emit(ResultWrapper.Success(result.dataResult))
            }
        }
    }
}
