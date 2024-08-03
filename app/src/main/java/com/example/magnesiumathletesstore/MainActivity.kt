package com.example.magnesiumathletesstore

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.rememberNavController
import com.example.magnesiumathletesstore.ui.navigation.BottomNavigationBar
import com.example.magnesiumathletesstore.ui.theme.MagnesiumAthletesStoreTheme
import com.example.magnesiumathletesstore.ui.navigation.Navigation
import com.example.magnesiumathletesstore.viewmodel.ShopifyViewModel
import dagger.hilt.android.AndroidEntryPoint

@OptIn(ExperimentalMaterial3Api::class)
@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    private val shopifyViewModel: ShopifyViewModel by viewModels()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MagnesiumAthletesStoreTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val navController = rememberNavController()
                    var expanded by remember { mutableStateOf(false) }
                    Scaffold(

                        topBar = {
                            TopAppBar(
                                title = {
                                    Row (
                                         verticalAlignment = Alignment.CenterVertically
                            ){
                                Icon(
                                    imageVector = Icons.Filled.AccountCircle,
                                    contentDescription = null,
                                    modifier = Modifier
                                        .size(48.dp)
                                        .padding(end = 8.dp)
                                )
                                        Image(
                                            painter = painterResource(id = R.drawable.mainlogo),
                                            contentDescription = null,
                                            modifier = Modifier.fillMaxWidth()
                                        )
                                        Text("Magnesium Athletes Store")
                                    }
                                },
                                actions = {
                                    IconButton(onClick = { navController.navigate("cart") }) {
                                        Icon(
                                            painter = painterResource(id = R.drawable.ic_cart),
                                            contentDescription = "Cart"
                                        )
                                    }
                                    IconButton(onClick = { expanded = true }) {
                                        Icon(
                                            painter = painterResource(id = R.drawable.ic_menu),
                                            contentDescription = "Menu"
                                        )
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
                                            text = { Text("Contact") },
                                            onClick = {
                                                navController.navigate("contact")
                                                expanded = false
                                            })
                                    }
                                }
                            )
                        },
                        bottomBar = { BottomNavigationBar(navController = navController) }
                    ) { paddingValues ->
                        Navigation(
                            navController = navController,
                            shopifyViewModel = shopifyViewModel,
                            modifier = Modifier.padding(paddingValues)
                        )
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    MagnesiumAthletesStoreTheme {
        // Placeholder for preview
    }
}