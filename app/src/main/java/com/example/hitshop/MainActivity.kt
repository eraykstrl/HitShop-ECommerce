package com.example.hitshop

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.hitshop.ui.screens.AppNavigation
import com.example.hitshop.ui.theme.HitShopTheme
import com.example.hitshop.ui.viewmodels.DetailViewModel
import com.example.hitshop.ui.viewmodels.FavoriteViewModel
import com.example.hitshop.ui.viewmodels.MainViewModel
import com.example.hitshop.ui.viewmodels.ShoppingCartViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    val mainViewModel : MainViewModel by viewModels()
    val detailViewModel : DetailViewModel by viewModels()
    val shoppingCartViewModel : ShoppingCartViewModel by viewModels()
    val favoriteViewModel : FavoriteViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            HitShopTheme {
                AppNavigation(
                    mainViewModel = mainViewModel,
                    detailViewModel = detailViewModel,
                    shoppingCartViewModel = shoppingCartViewModel,
                    favoriteViewModel = favoriteViewModel
                )
            }
        }
    }
}

