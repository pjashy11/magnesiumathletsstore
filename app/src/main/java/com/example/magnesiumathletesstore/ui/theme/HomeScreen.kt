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
import com.example.magnesiumathletesstore.ui.theme.TopAppBarComponent
import com.example.magnesiumathletesstore.viewmodel.ShopifyViewModel
import com.shopify.buy3.Storefront

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(navController: NavController, viewModel: ShopifyViewModel) {
        val products by viewModel.products.collectAsState()

        MagnesiumAthletesStoreTheme {
                Scaffold(
                        topBar = { TopAppBarComponent(navController = navController) }
                ) { paddingValues ->
                        Column(
                                modifier = Modifier
                                        .fillMaxSize()
                                        .padding(paddingValues)
                                        .padding(16.dp)
                        ) {
                                Text(text = "Welcome to the Magnesium Athletes Store")

                                Spacer(modifier = Modifier.height(8.dp))

                                HighlightProductsSection(navController = navController, products = products)
                        }
                }
        }
}

@Composable
fun HighlightProductsSection(navController: NavController, products: List<Storefront.Product>) {
        Column {
                Text(text = "Highlighted Products", style = MaterialTheme.typography.titleMedium)
                Spacer(modifier = Modifier.height(8.dp))

                LazyRow(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                        items(products) { product ->
                                ProductCard(navController, product)
                        }
                }
        }
}

@Composable
fun ProductCard(navController: NavController, product: Storefront.Product) {
        Card(
                modifier = Modifier
                        .width(150.dp)
                        .height(200.dp)
                        .padding(8.dp),
                onClick = {
                        navController.navigate("product/${product.id}")
                }
        ) {
                Column(
                        modifier = Modifier
                                .fillMaxSize()
                                .padding(8.dp),
                        verticalArrangement = Arrangement.Center
                ) {
                        product.images.edges.firstOrNull()?.node?.src?.let { imageUrl ->
                                AsyncImage(
                                        model = imageUrl,
                                        contentDescription = product.title,
                                        modifier = Modifier.height(100.dp),
                                        contentScale = ContentScale.Crop
                                )
                        }
                        Spacer(modifier = Modifier.height(8.dp))
                        Text(text = product.title, style = MaterialTheme.typography.bodyMedium)
                        Spacer(modifier = Modifier.height(4.dp))
                        val price = product.variants.edges.firstOrNull()?.node?.price?.amount ?: "N/A"
                        val currency = product.variants.edges.firstOrNull()?.node?.price?.currencyCode ?: ""
                        Text(text = "$price $currency", style = MaterialTheme.typography.bodyMedium)
                }
        }
}
