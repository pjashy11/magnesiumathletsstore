package com.example.magnesiumathletesstore.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.magnesiumathletesstore.repository.ShopifyRepository
import com.example.magnesiumathletesstore.GetAllProductsQuery
import com.example.magnesiumathletesstore.GetAllCollectionsQuery
import com.example.magnesiumathletesstore.GetProductByIdQuery
import com.example.magnesiumathletesstore.GetCollectionByIdQuery
import com.example.magnesiumathletesstore.model.CartItem
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ShopifyViewModel @Inject constructor(
    private val repository: ShopifyRepository
) : ViewModel() {

    private val _products = MutableStateFlow<List<GetAllProductsQuery.Edge?>>(emptyList())
    val products: StateFlow<List<GetAllProductsQuery.Edge?>> = _products

    private val _collections = MutableStateFlow<List<GetAllCollectionsQuery.Edge?>>(emptyList())
    val collections: StateFlow<List<GetAllCollectionsQuery.Edge?>> = _collections

    private val _cartItems = MutableStateFlow<List<CartItem>>(emptyList())
    val cartItems: StateFlow<List<CartItem>> = _cartItems

    private val collectionCache = mutableMapOf<String, MutableStateFlow<GetCollectionByIdQuery.Node?>>()
    private val productCache = mutableMapOf<String, MutableStateFlow<GetProductByIdQuery.Node?>>()

    init {
        loadProducts()
        loadCollections()
        loadCartItems()
    }

    fun loadProducts() {
        viewModelScope.launch {
            val products = repository.getAllProducts()
            _products.value = products
        }
    }

    fun loadCollections() {
        viewModelScope.launch {
            val collections = repository.getAllCollections()
            _collections.value = collections
        }
    }

    fun loadCartItems() {
        // This is just a placeholder implementation. Replace it with your actual implementation.
        viewModelScope.launch {
            val items = listOf(
                CartItem("1", "Product 1", 19.99, "https://via.placeholder.com/150", 2),
                CartItem("2", "Product 2", 29.99, "https://via.placeholder.com/150", 1)
            )
            _cartItems.value = items
        }
    }

    fun getProductById(productId: String): StateFlow<GetProductByIdQuery.Node?> {
        // Check if the product is already cached
        return productCache.getOrPut(productId) {
            MutableStateFlow<GetProductByIdQuery.Node?>(null).also { stateFlow ->
                viewModelScope.launch {
                    val product = repository.getProductById(productId).firstOrNull()
                    stateFlow.value = product
                }
            }
        }
    }

    fun getCollectionById(collectionId: String): StateFlow<GetCollectionByIdQuery.Node?> {
        // Check if the collection is already cached
        return collectionCache.getOrPut(collectionId) {
            MutableStateFlow<GetCollectionByIdQuery.Node?>(null).also { stateFlow ->
                viewModelScope.launch {
                    val collection = repository.getCollectionById(collectionId).firstOrNull()
                    stateFlow.value = collection
                }
            }
        }
    }
}
