package com.check.demo.ui.product.details

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.check.demo.datasource.model.ProductInfo
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class DetailsViewModel : ViewModel() {

    private val _uiState = MutableStateFlow(UiState())
    val uiState = _uiState.asStateFlow()

    fun setProduct(product: ProductInfo) {
        viewModelScope.launch {
            _uiState.emit(
                _uiState.value.copy(
                    iconUrl = product.iconUrl,
                    name = product.name,
                    price = product.price,
                    currency = product.currency,
                    rate = product.rating,
                    date = product.date,
                    description = product.description,
                    longDescription = product.longDescription
                )
            )
        }
    }

    fun onExpandClick() {
        viewModelScope.launch {
            _uiState.emit(
                _uiState.value.copy(
                    isExpanded = !_uiState.value.isExpanded
                )
            )
        }
    }

    data class UiState(
        val iconUrl: String? = null,
        val name: String = "",
        val price: String = "",
        val currency: String = "",
        val rate: Double? = null,
        val date: String? = null,
        val description: String? = null,
        val longDescription: String? = null,
        val isExpanded: Boolean = false
    )

}


