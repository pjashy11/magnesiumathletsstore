package com.example.magnesiumathletesstore.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.magnesiumathletesstore.viewmodel.ShopifyViewModel

@Composable
fun ProductsScreen(navController: NavController, viewModel: ShopifyViewModel, collectionId: String) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Text(text = "Products", style = MaterialTheme.typography.headlineSmall)

        Spacer(modifier = Modifier.height(16.dp))

        // TODO: Add a composable to display products and navigate to ProductScreen
    }
}
