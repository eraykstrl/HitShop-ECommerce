package com.example.hitshop.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.hitshop.R
import com.example.hitshop.data.entity.Product
import com.example.hitshop.ui.components.CustomCircularProgressBar
import com.example.hitshop.ui.components.CustomFavoriteItem
import com.example.hitshop.ui.components.CustomMainBottomAppBar
import com.example.hitshop.ui.components.CustomTopAppBar
import com.example.hitshop.ui.viewmodels.FavoriteViewModel
import com.google.gson.Gson

@Composable
fun FavoriteScreen(
    favoriteViewModel : FavoriteViewModel,
    navController: NavController
) {

    val favoriteList = favoriteViewModel.favoriteLiveData.observeAsState(listOf())
    val isLoading = favoriteViewModel.isLoadingLiveData.observeAsState()

    LaunchedEffect(key1 = true) {
        favoriteViewModel.getAllFavorites()
    }



    Scaffold(
        topBar = {
            CustomTopAppBar(
                screen = stringResource(id = R.string.first_topbar_text)
            )
        },
        bottomBar = {
            CustomMainBottomAppBar(
                onClickItem = {
                    screen ->
                    if(screen == 0) {
                        navController.navigate("mainScreen")
                    }
                    else if(screen == 1) {
                        navController.navigate("shoppingCartScreen")

                    }
                },
                screen = 2
            )
        }

    ) {
        pad ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(color = colorResource(id = R.color.background_color))
                .padding(pad)
                .padding(all = 8.dp)
        ) {
            if(!favoriteList.value.isNullOrEmpty()) {
                if(isLoading.value == false) {
                    Spacer(modifier = Modifier.size(16.dp))
                    LazyVerticalGrid(
                        columns = GridCells.Fixed(if(favoriteList.value.size > 1) 2 else 1),
                        modifier = Modifier
                            .weight(1f),
                        horizontalArrangement = Arrangement.spacedBy(8.dp),
                        verticalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        items(favoriteList.value) { favorite ->
                            val product = Product(
                                id = favorite.id,
                                name = favorite.name,
                                category = favorite.category,
                                brand = favorite.brand,
                                price = favorite.price,
                                image = favorite.image
                            )
                            val jsonProduct = Gson().toJson(product)
                            CustomFavoriteItem(
                                favorite,
                                onItemClick = {
                                    navController.navigate("detailScreen/$jsonProduct") {
                                    }
                                },
                                deleteFromFavorite = {
                                    favoriteViewModel.deleteFavorites(
                                        favorite_id = favorite.favorite_id
                                    )
                                    favoriteViewModel.getAllFavorites()
                                }
                            )
                        }

                    }
                }
                else {
                    Column(
                        modifier = Modifier
                            .fillMaxSize(),
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        CustomCircularProgressBar()
                    }
                }
            }
            else {
                Column(
                    modifier = Modifier
                        .fillMaxSize(),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = stringResource(id = R.string.favorite_alert_text),
                        textAlign = TextAlign.Center,
                        fontSize = 32.sp,
                        fontWeight = FontWeight.Bold,
                        color = colorResource(id = R.color.product_text_color),
                        lineHeight = 40.sp
                    )
                }
            }
        }
    }
}