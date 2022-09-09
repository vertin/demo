package com.check.demo.api

import com.check.demo.api.model.ProductsError
import com.check.demo.api.model.ProductsResponse
import retrofit2.http.GET

interface Check24ApiService {

    @GET("products-test.json")
    suspend fun getProductList(): NetworkResponse<ProductsResponse, ProductsError>

}
