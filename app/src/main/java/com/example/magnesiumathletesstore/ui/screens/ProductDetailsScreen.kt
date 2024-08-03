package com.example.magnesiumathletesstore.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.example.magnesiumathletesstore.viewmodel.ShopifyViewModel


@Composable
fun ProductDetails(navController: NavController, viewModel: ShopifyViewModel, productId: String) {
    val product by viewModel.getProductById(productId).collectAsState(initial = null)

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        when (val productData = product?.onProduct) {
            null -> {
                CircularProgressIndicator()
            }
            else -> {
                Text(text = productData.title ?: "", style = MaterialTheme.typography.headlineSmall)
                Spacer(modifier = Modifier.height(8.dp))
                Text(text = productData.description ?: "", style = MaterialTheme.typography.bodyMedium)

                productData.images?.edges?.firstOrNull()?.node?.src?.let { imageUrl ->
                    Spacer(modifier = Modifier.height(16.dp))
                    AsyncImage(
                        model = imageUrl,
                        contentDescription = productData.title,
                        modifier = Modifier.fillMaxWidth(),
                        contentScale = ContentScale.Crop
                    )
                }

                productData.variants?.edges?.firstOrNull()?.node?.let { variant ->
                    Spacer(modifier = Modifier.height(16.dp))
                    Text(text = "Price: ${variant.price?.amount} ${variant.price?.currencyCode}", style = MaterialTheme.typography.bodyLarge)
                }
            }
        }
    }
}
