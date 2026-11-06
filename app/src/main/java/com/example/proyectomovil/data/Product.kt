package com.example.proyectomovil.data

data class Product(
    val id: Int,
    val name: String,
    val price: Double,
    val store: String,
    val saving: Double = 0.0
)

data class StorePrice(
    val store: String,
    val price: Double,
    val distanceKm: Double,
    val minutes: Int
)