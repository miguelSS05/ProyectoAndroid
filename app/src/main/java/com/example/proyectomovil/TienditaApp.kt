package com.example.proyectomovil

import android.widget.Toast
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.proyectomovil.screens.CartScreen
import com.example.proyectomovil.screens.DetailScreen
import com.example.proyectomovil.screens.HomeScreen
import com.example.proyectomovil.screens.PlaceholderScreen

private data class BottomItem(
    val route: String,
    val label: String,
    val icon: ImageVector
)

@Composable
fun TienditiaApp() {
    val navController = rememberNavController()
    val context = LocalContext.current
    val backStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = backStackEntry?.destination?.route

    val items = listOf(
        BottomItem("home", "Inicio", Icons.Default.Home),
        BottomItem("search", "Buscar", Icons.Default.Search),
        BottomItem("favorites", "Favoritos", Icons.Default.Favorite),
        BottomItem("cart", "Carrito", Icons.Default.ShoppingCart)
    )

    Scaffold(
        bottomBar = {
            NavigationBar {
                items.forEach { item ->
                    NavigationBarItem(
                        selected = currentRoute == item.route,
                        onClick = {
                            navController.navigate(item.route) {
                                popUpTo(navController.graph.findStartDestination().id) {
                                    saveState = true
                                }
                                launchSingleTop = true
                                restoreState = true
                            }
                        },
                        icon = { Icon(item.icon, contentDescription = item.label) },
                        label = { Text(item.label) }
                    )
                }
            }
        }
    ) { padding ->
        NavHost(
            navController = navController,
            startDestination = "home",
            modifier = Modifier.padding(padding)
        ) {
            composable("home") {
                HomeScreen(
                    onProductClick = { product ->
                        navController.navigate("detail/${product.id}")
                    },
                    onAdd = { product ->
                        Toast.makeText(context, "Agregado: ${product.name}", Toast.LENGTH_SHORT).show()
                    }
                )
            }

            composable(
                route = "detail/{productId}",
                arguments = listOf(navArgument("productId") { type = NavType.IntType })
            ) { entry ->
                val productId = entry.arguments?.getInt("productId") ?: 0
                DetailScreen(
                    productId = productId,
                    onBack = { navController.popBackStack() },
                    onAdd = {
                        Toast.makeText(context, "Producto agregado al carrito", Toast.LENGTH_SHORT).show()
                    }
                )
            }

            composable("cart") {
                CartScreen(
                    onFinalize = {
                        Toast.makeText(context, "Compra finalizada (simulación)", Toast.LENGTH_SHORT).show()
                    }
                )
            }

            composable("search") { PlaceholderScreen("Buscar") }
            composable("favorites") { PlaceholderScreen("Favoritos") }
        }
    }
}