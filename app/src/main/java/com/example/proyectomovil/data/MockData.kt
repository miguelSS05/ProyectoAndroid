package com.example.proyectomovil.data

object MockData {

    val products = listOf(
        Product(1, "Atún Dolores 140 g", 22.00, "Chedraui", 4.40),
        Product(2, "Arroz Verde Valle 1 kg", 22.00, "Walmart", 3.10),
        Product(3, "Leche Lala 1 L", 22.00, "Soriana", 2.00),
        Product(4, "Frijol Negro 1 kg", 28.50, "Chedraui", 3.20),
        Product(5, "Aceite Nutrioli 900 ml", 38.90, "Walmart", 5.10),
        Product(6, "Atún Dolores 140 g", 22.00, "Bodega Aurrerá", 4.40)
    )

    private val storePrices: Map<Int, List<StorePrice>> = mapOf(
        1 to listOf(
            StorePrice("Chedraui", 21.50, 2.8, 12),
            StorePrice("Walmart", 22.00, 2.8, 12),
            StorePrice("Soriana", 23.90, 2.8, 12)
        ),
        2 to listOf(
            StorePrice("Walmart", 21.90, 3.1, 14),
            StorePrice("Chedraui", 22.50, 2.8, 12),
            StorePrice("Soriana", 23.00, 4.0, 16)
        )
    )

    fun storePricesFor(productId: Int): List<StorePrice> {
        return storePrices[productId].orEmpty()
    }
}