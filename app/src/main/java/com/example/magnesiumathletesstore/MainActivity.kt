package com.example.magnesiumathletesstore

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.rememberNavController
import com.example.magnesiumathletesstore.ui.theme.BottomNavigationBar
import com.example.magnesiumathletesstore.ui.theme.MagnesiumAthletesStoreTheme
import com.example.magnesiumathletesstore.ui.theme.Navigation
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
                    Scaffold(
                        topBar = { TopAppBar(title = { Text("Magnesium Athletes Store") }) },
                        bottomBar = { BottomNavigationBar(navController = navController) }
                    ) { paddingValues ->
                        Navigation(navController = navController, shopifyViewModel = shopifyViewModel, modifier = Modifier.padding(paddingValues))
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
