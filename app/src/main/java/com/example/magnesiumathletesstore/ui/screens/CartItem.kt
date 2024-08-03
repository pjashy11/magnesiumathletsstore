package com.example.magnesiumathletesstore.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.magnesiumathletesstore.model.CartItem

@Composable
fun CartItem(cartItem: CartItem) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
    ) {
        Row(
            modifier = Modifier.padding(8.dp)
        ) {
            AsyncImage(
                model = cartItem.imageUrl,
                contentDescription = "Cart Item Image",
                modifier = Modifier.size(64.dp),
                contentScale = ContentScale.Crop
            )
            Spacer(modifier = Modifier.width(8.dp))
            Column {
                Text(text = cartItem.title, style = MaterialTheme.typography.bodySmall)
                Text(text = "$${cartItem.price}", style = MaterialTheme.typography.bodyMedium)
                Text(text = "Quantity: ${cartItem.quantity}", style = MaterialTheme.typography.bodyMedium)
            }
        }
    }
}
