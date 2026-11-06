package com.example.proyectomovil.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.proyectomovil.data.MockData
import java.util.Locale

@Composable
fun DetailScreen(
    productId: Int,
    onBack: () -> Unit,
    onAdd: () -> Unit
) {
    val product = MockData.products.find { it.id == productId }

    if (product == null) {
        Text("Producto no encontrado", modifier = Modifier.padding(16.dp))
        return
    }

    val prices = remember(productId) {
        MockData.storePricesFor(productId).sortedBy { it.price }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(16.dp)
    ) {
        TextButton(onClick = onBack) {
            Text("← Volver")
        }

        Text(
            text = product.name,
            style = MaterialTheme.typography.headlineSmall,
            color = MaterialTheme.colorScheme.primary,
            fontWeight = FontWeight.Bold
        )

        Text(
            text = "Desde $${String.format(Locale.getDefault(), "%.2f", product.price)}",
            style = MaterialTheme.typography.titleLarge,
            color = Color(0xFF2E7D32),
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(top = 8.dp)
        )

        prices.forEachIndexed { index, storePrice ->
            val isBest = index == 0
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 12.dp),
                colors = CardDefaults.cardColors(
                    containerColor = if (isBest) Color(0xFFE8F5E9) else MaterialTheme.colorScheme.surface
                )
            ) {
                Column(modifier = Modifier.padding(16.dp)) {
                    Text(
                        text = storePrice.store + if (isBest) " — MEJOR PRECIO" else "",
                        fontWeight = FontWeight.Bold
                    )
                    Text(
                        text = "$${String.format(Locale.getDefault(), "%.2f", storePrice.price)} · " +
                                "${storePrice.minutes} min en auto · ${storePrice.distanceKm} km"
                    )
                }
            }
        }

        Button(
            onClick = onAdd,
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 24.dp)
        ) {
            Text("Agregar al carrito")
        }
    }
}