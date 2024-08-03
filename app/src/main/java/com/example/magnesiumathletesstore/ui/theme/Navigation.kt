package com.example.magnesiumathletesstore.ui.theme

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.foundation.layout.padding
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.magnesiumathletesstore.ui.screens.*
import com.example.magnesiumathletesstore.viewmodel.ShopifyViewModel

@Composable
fun Navigation(
    navController: NavHostController = rememberNavController(),
    shopifyViewModel: ShopifyViewModel,
    paddingValues: PaddingValues
) {
    NavHost(
        navController = navController,
        startDestination = "splash",
        modifier = Modifier.padding(paddingValues)
    ) {
        composable("splash") {
            SplashScreen(navController = navController)
        }
        composable("home") {
            HomeScreen(navController = navController, viewModel = shopifyViewModel)
        }
        composable("collections") {
            CollectionsScreen(navController = navController, viewModel = shopifyViewModel)
        }
        composable("products") { backStackEntry ->
            val collectionId = backStackEntry.arguments?.getString("collectionId")
            collectionId?.let {
                ProductsScreen(
                    navController = navController,
                    viewModel = shopifyViewModel,
                    collectionId = it
                )
            }
        }
        composable("about") {
            AboutScreen()
        }
        composable("cart") {
            CartScreen(navController = navController, viewModel = shopifyViewModel)
        }
        composable("contact") {
            ContactScreen()
        }
        composable("product/{productId}") { backStackEntry ->
            val productId = backStackEntry.arguments?.getString("productId")
            productId?.let {
                ProductDetails(
                    navController = navController,
                    viewModel = shopifyViewModel,
                    productId = it
                )
            }
        }
        composable("checkout") {
            CheckoutScreen(navController = navController, viewModel = shopifyViewModel)
        }
    }
}
