package com.example.magnesiumathletesstore.ui.screens

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.magnesiumathletesstore.viewmodel.ShopifyViewModel
import com.example.magnesiumathletesstore.GetCollectionByIdQuery
import coil.compose.AsyncImage

@Composable
fun CollectionDetailsScreen(viewModel: ShopifyViewModel, collectionId: String, navController: NavController) {
    println("Displaying collection with ID: $collectionId")
    val collection by viewModel.getCollectionById(collectionId).collectAsState(initial = null)

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        when (val collectionData = collection) {
            null -> {
                CircularProgressIndicator()
                Text("Loading...")
            }
            else -> {
                collectionData.onCollection?.let {
                    Text(text = it.title ?: "", style = MaterialTheme.typography.headlineSmall)
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(text = it.description ?: "", style = MaterialTheme.typography.bodyMedium)

                    it.products?.edges?.let { products ->
                        Spacer(modifier = Modifier.height(16.dp))
                        Text(text = "Products", style = MaterialTheme.typography.headlineMedium)

                        CollectionProductsGrid(products, navController)
                    }
                } ?: run {
                    Text("Collection data not available.")
                }
            }
        }
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun CollectionProductsGrid(products: List<GetCollectionByIdQuery.Edge?>, navController: NavController) {
    LazyVerticalGrid(
        columns = GridCells.Adaptive(150.dp),
        contentPadding = PaddingValues(8.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        items(products) { productEdge ->
            productEdge?.node?.let { product ->
                CollectionProductCard(product, navController)
            }
        }
    }
}

@Composable
fun CollectionProductCard(product: GetCollectionByIdQuery.Node1, navController: NavController) {
    val productId = product.id
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .clickable { navController.navigate("product/$productId") }
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(8.dp),
            verticalArrangement = Arrangement.Center
        ) {
            product.images?.edges?.firstOrNull()?.node?.src?.let { imageUrl ->
                AsyncImage(
                    model = imageUrl,
                    contentDescription = product.title,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(150.dp),
                    contentScale = ContentScale.Crop
                )
            }
            Spacer(modifier = Modifier.height(8.dp))
            Text(text = product.title ?: "", style = MaterialTheme.typography.bodyMedium)
            Spacer(modifier = Modifier.height(4.dp))
            val price = product.variants?.edges?.firstOrNull()?.node?.price?.amount ?: "N/A"
            val currency = product.variants?.edges?.firstOrNull()?.node?.price?.currencyCode ?: ""
            Text(text = "$price $currency", style = MaterialTheme.typography.bodyMedium)
        }
    }
}
