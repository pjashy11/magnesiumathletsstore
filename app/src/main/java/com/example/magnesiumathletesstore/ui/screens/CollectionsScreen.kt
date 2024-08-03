package com.example.magnesiumathletesstore.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.magnesiumathletesstore.viewmodel.ShopifyViewModel

@Composable
fun CollectionsScreen(navController: NavController, viewModel: ShopifyViewModel) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Text(text = "Collections", style = MaterialTheme.typography.headlineSmall)

        Spacer(modifier = Modifier.height(16.dp))

        // TODO: Add a composable to display collections
    }
}
