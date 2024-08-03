package com.example.magnesiumathletesstore.ui.theme

import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.res.painterResource
import androidx.navigation.NavController
import com.example.magnesiumathletesstore.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopAppBarComponent(navController: NavController) {
    var expanded by remember { mutableStateOf(false) }

    TopAppBar(
        title = { Text(text = "Magnesium Athletes Store") },
        actions = {
            IconButton(onClick = { navController.navigate("cart") }) {
                Icon(painterResource(id = R.drawable.ic_cart), contentDescription = "Cart")
            }
            IconButton(onClick = { expanded = true }) {
                Icon(painterResource(id = R.drawable.ic_menu), contentDescription = "Menu")
            }
            DropdownMenu(
                expanded = expanded,
                onDismissRequest = { expanded = false }) {

                DropdownMenuItem(
                    text = { Text("Home") },
                    onClick = {
                        navController.navigate("home")
                        expanded = false
                    })
                DropdownMenuItem(
                    text = { Text("Collections") },
                    onClick = {
                        navController.navigate("collections")
                        expanded = false
                    })
                DropdownMenuItem(
                    text = { Text("Products") },
                    onClick = {
                        navController.navigate("products")
                        expanded = false
                    })
                DropdownMenuItem(
                    text = { Text("About") },
                    onClick = {
                        navController.navigate("about")
                        expanded = false
                    })
                DropdownMenuItem(
                    text = { Text("Contact") },
                    onClick = {
                        navController.navigate("contact")
                        expanded = false
                    })
            }
        }
    )
}

