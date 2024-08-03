package com.example.magnesiumathletesstore.ui.theme

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController

@Composable
fun DrawerContent(navController: NavHostController) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Text(text = "Menu", style = MaterialTheme.typography.headlineSmall)

        Spacer(modifier = Modifier.height(16.dp))

        NavigationItem(navController = navController, label = "Home", route = "home")
        NavigationItem(navController = navController, label = "Collections", route = "collections")
        NavigationItem(navController = navController, label = "Products", route = "products")
        NavigationItem(navController = navController, label = "About", route = "about")
        NavigationItem(navController = navController, label = "Cart", route = "cart")
        NavigationItem(navController = navController, label = "Contact", route = "contact")
    }
}

@Composable
fun NavigationItem(navController: NavHostController, label: String, route: String) {
    TextButton(onClick = { navController.navigate(route) }) {
        Text(text = label, style = MaterialTheme.typography.headlineSmall)
    }
}
