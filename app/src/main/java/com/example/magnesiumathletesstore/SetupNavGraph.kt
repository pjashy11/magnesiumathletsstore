package com.example.magnesiumathletesstore

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.magnesiumathletesstore.ui.screens.*
import com.example.magnesiumathletesstore.viewmodel.ShopifyViewModel

@Composable
fun SetupNavGraph(navController: NavHostController, shopifyViewModel: ShopifyViewModel) {
    NavHost(navController = navController, startDestination = "splash") {
        composable("splash") { SplashScreen(navController) }
        composable("home") { HomeScreen(navController, shopifyViewModel) }
        composable("collections") { CollectionsScreen(navController, shopifyViewModel) }
        composable("products/{collectionId}") { backStackEntry ->
            val collectionId = backStackEntry.arguments?.getString("collectionId") ?: ""
            ProductsScreen(navController, shopifyViewModel, collectionId)
        }
        composable("product/{productId}") { backStackEntry ->
            val productId = backStackEntry.arguments?.getString("productId") ?: ""
            ProductDetails(navController, shopifyViewModel, productId)
        }
        composable("cart") { CartScreen(navController, shopifyViewModel) }
        composable("checkout") { CheckoutScreen(navController, shopifyViewModel) }
    }
}
