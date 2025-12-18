package com.example.hitshop.ui.screens

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.hitshop.R
import com.example.hitshop.ui.components.CustomCircularProgressBar
import com.example.hitshop.ui.components.CustomMainBottomAppBar
import com.example.hitshop.ui.components.CustomOutlinedTextField
import com.example.hitshop.ui.components.CustomProductItem
import com.example.hitshop.ui.components.CustomTopAppBar
import com.example.hitshop.ui.viewmodels.MainViewModel
import com.google.gson.Gson

@Composable
fun MainScreen(
    mainViewModel: MainViewModel,
    navController: NavController
) {

    val productList = mainViewModel.productLiveData.observeAsState(listOf())
    val favoriteList = mainViewModel.favoriteLiveData.observeAsState(listOf())
    val isLoading = mainViewModel.isLoadingLiveData.observeAsState()

    val searchText = remember { mutableStateOf("") }

    LaunchedEffect(key1 = true) {
        mainViewModel.getAllProducts()
        mainViewModel.getAllFavorites()
    }
    mainViewModel.insertProductToRoom(productList = productList.value)

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
                    if(screen == 1) {
                        navController.navigate("shoppingCartScreen")
                    }
                    else if(screen == 2) {
                        navController.navigate("favoriteScreen")

                    }
                },
                screen = 0
            )
        }

    ) {
        paddingVal ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(color = colorResource(id = R.color.background_color))
                .padding(paddingVal)
                .padding(all = 8.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            if (isLoading.value == false) {
                CustomOutlinedTextField(
                    hint = stringResource(id = R.string.search_product_text),
                    value = searchText.value,
                    onValueChange = { it ->
                        searchText.value = it
                        mainViewModel.searchProduct(searchText = searchText.value)
                    }
                )
                Spacer(
                    modifier = Modifier
                        .height(16.dp)
                        .background(colorResource(id = R.color.white))
                )
                LazyVerticalGrid(
                    columns = GridCells.Fixed(2),
                    modifier = Modifier
                        .weight(1f)
                        .padding(top = 8.dp),
                    horizontalArrangement = Arrangement.spacedBy(8.dp),
                    verticalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    items(productList.value) { product ->
                        var temp = false
                        var favorite_id: Int? = null
                        favoriteList.value?.forEach {
                            if (it.id == product.id) {
                                temp = true
                                favorite_id = it.favorite_id
                            }
                        }

                        val jsonProduct = Gson().toJson(product)
                        CustomProductItem(
                            product,
                            onItemClick = {
                                navController.navigate("detailScreen/$jsonProduct") {
                                }
                            },
                            addToFavorite = {
                                Log.e("Main Screen","Add Favorite Basıldı")
                                if (temp == true && favorite_id != null) {
                                    Log.e("Main Screen", "Siliniyor olması gerekiyor")
                                    mainViewModel.deleteFavorites(favorite_id = favorite_id)
                                } else {
                                    Log.e("Main Screen", "Ekleniyor olması gerekiyor")
                                    mainViewModel.insertToFavorites(
                                        id = product.id,
                                        name = product.name,
                                        image = product.image,
                                        category = product.category,
                                        price = product.price,
                                        brand = product.brand
                                    )
                                }
                            },
                            favorite = temp
                        )
                    }
                }
            }
            else {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                ) {
                    CustomCircularProgressBar()
                }
            }
        }
    }

}