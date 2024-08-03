package com.example.magnesiumathletesstore.ui.theme

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.ui.graphics.vector.ImageVector

sealed class BottomNavItem(val route: String, val title: String, val icon: ImageVector) {
    object Home : BottomNavItem("home", "Home", Icons.Default.Home)
    object Collections : BottomNavItem("collections", "Collections", Icons.Default.List)
    object Cart : BottomNavItem("cart", "Cart", Icons.Default.ShoppingCart)
    object About : BottomNavItem("about", "About", Icons.Default.Info)
    object Contact : BottomNavItem("contact", "Contact", Icons.Default.Call)
}
