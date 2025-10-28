package com.example.hitshop.ui.screens

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.hitshop.data.entity.Product
import com.example.hitshop.ui.viewmodels.DetailViewModel
import com.example.hitshop.ui.viewmodels.FavoriteViewModel
import com.example.hitshop.ui.viewmodels.MainViewModel
import com.example.hitshop.ui.viewmodels.ShoppingCartViewModel
import com.google.gson.Gson

@Composable
fun AppNavigation(
    mainViewModel: MainViewModel,
    detailViewModel: DetailViewModel,
    shoppingCartViewModel: ShoppingCartViewModel,
    favoriteViewModel: FavoriteViewModel
) {

    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = "mainScreen") {

        composable("mainScreen") {
            MainScreen(
                mainViewModel = mainViewModel,
                navController = navController
            )
        }

        composable("detailScreen/{product}",
            arguments = listOf(
                navArgument("product") { type = NavType.StringType  }
            )
            ) {
            val product = it.arguments?.getString("product")
            val fromJson = Gson().fromJson(product,Product::class.java)
            if(fromJson != null)
            {
                DetailScreen(
                    detailViewModel = detailViewModel,
                    navController = navController,
                    product = fromJson
                )
            }
        }

        composable("shoppingCartScreen") {
            ShoppingCartScreen(
                shoppingCartViewModel = shoppingCartViewModel,
                navController = navController
            )
        }

        composable("favoriteScreen") {
            FavoriteScreen(
                favoriteViewModel = favoriteViewModel,
                navController = navController
            )
        }
    }
}