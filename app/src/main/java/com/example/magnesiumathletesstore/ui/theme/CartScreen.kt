package com.example.magnesiumathletesstore.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.magnesiumathletesstore.ui.theme.CartItem
import com.example.magnesiumathletesstore.viewmodel.ShopifyViewModel

@Composable
fun CartScreen(navController: NavController, viewModel: ShopifyViewModel) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Text(text = "Cart", style = MaterialTheme.typography.headlineSmall)

        Spacer(modifier = Modifier.height(16.dp))

        val cartItems = viewModel.cartItems.collectAsState().value
        cartItems.forEach { cartItem ->
            CartItem(cartItem = cartItem)
        }
    }
}
