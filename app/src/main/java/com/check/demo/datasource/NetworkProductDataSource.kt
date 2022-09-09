package com.check.demo.datasource

import com.check.demo.api.Check24ApiService
import com.check.demo.api.NetworkResponse
import com.check.demo.api.model.ProductsResponse
import com.check.demo.datasource.model.OverviewInfo
import com.check.demo.datasource.model.ProductInfo
import com.check.demo.datasource.model.Products
import com.check.demo.util.DateUtil
import com.check.demo.util.DispatcherManager
import kotlinx.coroutines.withContext

class NetworkProductDataSource(
    private val dispatcherManager: DispatcherManager,
    private val apiService: Check24ApiService
) : ProductDataSource {

    override suspend fun getProductList(): DataResultWrapper<Products, String> =
        withContext(dispatcherManager.IO) {
            val result = apiService.getProductList()
            when (result) {
                is NetworkResponse.ApiError -> DataResultWrapper.Error("ApiError")
                is NetworkResponse.NetworkError -> DataResultWrapper.Error("NetworkError")
                is NetworkResponse.Success -> DataResultWrapper.Success(
                    mapProducts(result.body)
                )
                is NetworkResponse.UnknownError -> DataResultWrapper.Error("Unknown error")
            }
        }

    private fun mapProducts(response: ProductsResponse): Products = Products(
        OverviewInfo(response.header.headerTitle, response.header.headerDescription),
        response.productInfo.map {
            ProductInfo(
                it.id,
                it.name,
                it.imageURL,
                it.price.value.toString(),
                it.price.currency,
                DateUtil.formatDate(it.releaseDate * 1000),
                it.description,
                it.longDescription,
                it.rating
            )
        }
    )
}
