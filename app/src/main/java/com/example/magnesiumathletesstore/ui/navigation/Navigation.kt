package com.example.magnesiumathletesstore.ui.navigation

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.magnesiumathletesstore.ui.screens.*
import com.example.magnesiumathletesstore.viewmodel.ShopifyViewModel

@Composable
fun Navigation(
    navController: NavHostController,
    shopifyViewModel: ShopifyViewModel,
    modifier: Modifier = Modifier,
    paddingValues: PaddingValues = PaddingValues(0.dp)
) {
    NavHost(
        navController = navController,
        startDestination = "splash",
        modifier = modifier.padding(paddingValues)
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
        composable(
            route = "product/{productId}",
            arguments = listOf(navArgument("productId") { type = NavType.StringType })
        ) { backStackEntry ->
            val productId = backStackEntry.arguments?.getString("productId") ?: ""
            ProductDetails(navController = navController, viewModel = shopifyViewModel, productId = productId)
        }
        composable(
            route = "collection/{collectionId}",
            arguments = listOf(navArgument("collectionId") { type = NavType.StringType })
        ) { backStackEntry ->
            val collectionId = backStackEntry.arguments?.getString("collectionId") ?: ""
            CollectionDetailsScreen(viewModel = shopifyViewModel, collectionId = collectionId, navController = navController)
        }
        composable("cart") {
            CartScreen(navController = navController, viewModel = shopifyViewModel)
        }
        composable("checkout") {
            CheckoutScreen(navController = navController, viewModel = shopifyViewModel)
        }
    }
}
