package com.example.magnesiumathletesstore.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.example.magnesiumathletesstore.ui.theme.MagnesiumAthletesStoreTheme
import com.example.magnesiumathletesstore.viewmodel.ShopifyViewModel
import com.example.magnesiumathletesstore.GetAllProductsQuery
import com.example.magnesiumathletesstore.GetAllCollectionsQuery
import java.net.URLEncoder
import java.nio.charset.StandardCharsets

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(navController: NavController, viewModel: ShopifyViewModel) {
        val products by viewModel.products.collectAsState()
        val collections by viewModel.collections.collectAsState()

        MagnesiumAthletesStoreTheme {
                Scaffold(

                ) { paddingValues ->
                        Column(
                                modifier = Modifier
                                        .fillMaxSize()
                                        .padding(paddingValues)
                                        .padding(16.dp)
                        ) {
                                Spacer(modifier = Modifier.height(8.dp))

                                HighlightCollectionsSection(navController = navController, collections = collections)
                                Spacer(modifier = Modifier.height(8.dp))
                                HighlightProductsSection(navController = navController, products = products)
                        }
                }
        }
}

@Composable
fun HighlightCollectionsSection(navController: NavController, collections: List<GetAllCollectionsQuery.Edge?>) {
        Column {
                Text(text = "Highlighted Collections", style = MaterialTheme.typography.titleMedium)
                Spacer(modifier = Modifier.height(8.dp))

                LazyRow(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                        items(collections) { collectionEdge ->
                                collectionEdge?.node?.let { collection ->
                                        CollectionCard(navController, collection)
                                }
                        }
                }
        }
}

@Composable
fun HighlightProductsSection(navController: NavController, products: List<GetAllProductsQuery.Edge?>) {
        Column {
                Text(text = "Highlighted Products", style = MaterialTheme.typography.titleMedium)
                Spacer(modifier = Modifier.height(8.dp))

                LazyRow(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                        items(products) { productEdge ->
                                productEdge?.node?.let { product ->
                                        HighlightedProductCard(product, navController)
                                }
                        }
                }
        }
}

@Composable
fun CollectionCard(navController: NavController, collection: GetAllCollectionsQuery.Node) {
        val collectionId = collection.id
        println("Navigating to collection with ID: $collectionId")
        Card(
                modifier = Modifier
                        .width(150.dp)
                        .height(200.dp)
                        .padding(8.dp),
                onClick = {
                        val encodedCollectionId = URLEncoder.encode(collectionId, StandardCharsets.UTF_8.toString())
                        navController.navigate("collection/$encodedCollectionId")
                }
        ) {
                Column(
                        modifier = Modifier
                                .fillMaxSize()
                                .padding(8.dp),
                        verticalArrangement = Arrangement.Center
                ) {
                        collection.image?.src?.let { imageUrl ->
                                AsyncImage(
                                        model = imageUrl,
                                        contentDescription = collection.title,
                                        modifier = Modifier.height(100.dp),
                                        contentScale = ContentScale.Crop
                                )
                        }
                        Spacer(modifier = Modifier.height(8.dp))
                        Text(text = collection.title ?: "", style = MaterialTheme.typography.bodyMedium)
                }
        }
}

@Composable
fun HighlightedProductCard(product: GetAllProductsQuery.Node, navController: NavController) {
        val productId = product.id
        Card(
                modifier = Modifier
                        .width(150.dp)
                        .height(200.dp)
                        .padding(8.dp),
                onClick = {
                        val encodedProductId = URLEncoder.encode(productId, StandardCharsets.UTF_8.toString())
                        navController.navigate("product/$encodedProductId")
                }
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
                                        modifier = Modifier.height(100.dp),
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