package com.example.magnesiumathletesstore.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.magnesiumathletesstore.repository.ShopifyRepository
import com.shopify.buy3.Storefront
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ShopifyViewModel @Inject constructor(
    private val repository: ShopifyRepository
) : ViewModel() {

    private val _products = MutableStateFlow<List<Storefront.Product>>(emptyList())
    val products: StateFlow<List<Storefront.Product>> = _products

    init {
        loadProducts()
    }

    fun loadProducts() {
        viewModelScope.launch {
            val products = repository.getAllProducts()
            _products.value = products
        }
    }

    fun getProductById(productId: String): Storefront.Product? {
        return _products.value.find { it.id.toString() == productId }
    }
}
