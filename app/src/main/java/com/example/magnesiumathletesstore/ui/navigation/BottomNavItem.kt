package com.example.magnesiumathletesstore.ui.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.List
import androidx.compose.material.icons.filled.*
import androidx.compose.ui.graphics.vector.ImageVector

sealed class BottomNavItem(val route: String, val title: String, val icon: ImageVector) {
    object Home : BottomNavItem("home", "Home", Icons.Default.Home)
    object Collections : BottomNavItem("collections", "Collections", Icons.AutoMirrored.Filled.List)
    object Cart : BottomNavItem("cart", "Cart", Icons.Default.ShoppingCart)
        object Contact : BottomNavItem("contact", "Contact", Icons.Default.Call)
}
