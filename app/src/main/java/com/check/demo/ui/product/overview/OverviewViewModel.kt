package com.check.demo.ui.product.overview

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.check.demo.datasource.model.OverviewInfo
import com.check.demo.datasource.model.ProductInfo
import com.check.demo.datasource.model.Products
import com.check.demo.repository.ProductsRepository
import com.check.demo.repository.ResultWrapper
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class OverviewViewModel(private val repository: ProductsRepository) : ViewModel() {

    private val _state = MutableStateFlow(UiState())
    val uiState = _state.asStateFlow()

    init {
        loadProducts()
    }

    private fun loadProducts() {
        viewModelScope.launch {
            repository.getProducts().collect { result ->
                when (result) {
                    is ResultWrapper.Error -> onError(result)
                    is ResultWrapper.Loading -> onLoading()
                    is ResultWrapper.Success -> onSuccess(result)
                }
            }
        }
    }

    fun refresh() {
        viewModelScope.launch {
            _state.emit(_state.value.copy(isRefreshing = true))
            loadProducts()
        }
    }

    private suspend fun onSuccess(result: ResultWrapper.Success<Products, String>) {
        _state.emit(
            _state.value.copy(
                data = result.data.productList,
                isLoading = false,
                isRefreshing = false,
                error = null,
                overviewInfo = result.data.overviewInfo
            )
        )
    }

    private suspend fun onLoading() {
        if (!_state.value.isRefreshing) _state.emit(
            _state.value.copy(
                isLoading = true,
                error = null
            )
        )
    }

    private suspend fun onError(result: ResultWrapper.Error<Products, String>) {
        _state.emit(
            _state.value.copy(
                isLoading = false,
                isRefreshing = false,
                data = emptyList(),
                overviewInfo = null,
                error = result.error
            )
        )
    }

    data class UiState(
        val isLoading: Boolean = false,
        val isRefreshing: Boolean = false,
        val error: String? = null,
        val data: List<ProductInfo> = emptyList(),
        val overviewInfo: OverviewInfo? = null
    )
}
