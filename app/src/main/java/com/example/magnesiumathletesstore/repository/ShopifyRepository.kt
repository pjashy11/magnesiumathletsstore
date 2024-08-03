package com.example.magnesiumathletesstore.repository

import com.apollographql.apollo.ApolloClient
import com.example.magnesiumathletesstore.GetAllProductsQuery
import com.example.magnesiumathletesstore.GetAllCollectionsQuery
import com.example.magnesiumathletesstore.GetCollectionByIdQuery
import com.example.magnesiumathletesstore.GetProductByIdQuery
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class ShopifyRepository @Inject constructor(
    private val apolloClient: ApolloClient
) {
    suspend fun getAllProducts(): List<GetAllProductsQuery.Edge?> {
        val response = apolloClient.query(GetAllProductsQuery()).execute()
        return response.data?.products?.edges?.filterNotNull() ?: emptyList()
    }

    suspend fun getAllCollections(): List<GetAllCollectionsQuery.Edge?> {
        val response = apolloClient.query(GetAllCollectionsQuery()).execute()
        return response.data?.collections?.edges?.filterNotNull() ?: emptyList()
    }

    fun getProductById(productId: String): Flow<GetProductByIdQuery.Node?> = flow {
        println("Querying product with ID: $productId")
        val response = apolloClient.query(GetProductByIdQuery(productId)).execute()
        emit(response.data?.node)
    }

    fun getCollectionById(collectionId: String): Flow<GetCollectionByIdQuery.Node?> = flow {
        println("Querying collection with ID: $collectionId")
        val response = apolloClient.query(GetCollectionByIdQuery(collectionId)).execute()
        emit(response.data?.node)
    }
}

