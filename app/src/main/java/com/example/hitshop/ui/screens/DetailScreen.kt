package com.example.hitshop.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.hitshop.data.entity.Product
import com.example.hitshop.ui.components.CustomTopAppBar
import com.example.hitshop.ui.viewmodels.DetailViewModel
import com.example.hitshop.R
import com.example.hitshop.ui.components.CustomBottomAppBar
import com.example.hitshop.ui.components.CustomDetailProductItem

@Composable
fun DetailScreen(
    detailViewModel: DetailViewModel,
    navController: NavController,
    product: Product
) {

    val productQuantity = remember { mutableStateOf(0) }

    Scaffold(
        topBar = {
            CustomTopAppBar(screen = stringResource(id = R.string.add_to_card))
        },
        bottomBar = {
            CustomBottomAppBar(
                product = product,
                addToCart = {
                    if(productQuantity.value >0 ) {
                        detailViewModel.insertProductToCart(
                            name = product.name,
                            image = product.image,
                            category = product.category,
                            price = product.price,
                            brand = product.brand,
                            orderQuantity = productQuantity.value,
                            username = "eray.kstrl",
                            onComplete = {
                                navController.navigate("shoppingCartScreen")
                            }
                        )
                    }
                }
            )
        }
    ) {
        paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(all = 16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            CustomDetailProductItem(
                product = product,
                quantity = {
                    productQuantity.value = it
                },
            )
        }
    }


}