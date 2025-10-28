package com.example.hitshop.ui.screens

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.SnackbarResult
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.hitshop.R
import com.example.hitshop.ui.components.CustomCircularProgressBar
import com.example.hitshop.ui.components.CustomMainBottomAppBar
import com.example.hitshop.ui.components.CustomOrderButton
import com.example.hitshop.ui.components.CustomShoppingCartItem
import com.example.hitshop.ui.components.CustomTopAppBar
import com.example.hitshop.ui.viewmodels.ShoppingCartViewModel
import kotlinx.coroutines.launch

@Composable
fun ShoppingCartScreen(
    shoppingCartViewModel: ShoppingCartViewModel,
    navController: NavController
) {

    val allList = shoppingCartViewModel.productCart.observeAsState(listOf())
    val isLoading = shoppingCartViewModel.isLoading.observeAsState()
    val snackbarHostState = remember { SnackbarHostState() }
    val context = LocalContext.current
    val scope = rememberCoroutineScope()

    val totalOrderPrice = remember() { mutableStateOf(0) }

    if(!allList.value.isNullOrEmpty()) {
        totalOrderPrice.value = 0
        allList.value.forEach {
            totalOrderPrice.value += (it.price * it.order_quantity)
        }
    }
    LaunchedEffect(key1 = true) {
        shoppingCartViewModel.getAllCart("eray.kstrl")
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
                    else if(screen == 2) {
                        navController.navigate("favoriteScreen")
                    }
                },
                screen = 1
            )
        },
        snackbarHost = {
            SnackbarHost(hostState = snackbarHostState)
        }
    ) {
        pad ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(color = colorResource(id = R.color.background_color))
                .padding(pad),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            if(isLoading.value == false) {
                LazyColumn(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier
                        .weight(1f)
                ) {
                    items(allList.value) { item ->
                        Log.e("ShoppingCartScreen Items", "${item.cart_id}")
                        CustomShoppingCartItem(
                            product = item,
                            snackbarItem = {
                                scope.launch {
                                    val sb = snackbarHostState.showSnackbar(
                                        message = context.getString(R.string.delete_all_alert),
                                        actionLabel = context.getString(R.string.snackbar_action_label)
                                    )
                                    if (sb == SnackbarResult.ActionPerformed) {
                                        shoppingCartViewModel.deleteFromCart(
                                            cart_id = item.cart_id,
                                            username = "eray.kstrl"
                                        )
                                        shoppingCartViewModel.getAllCart(username = "eray.kstrl")
                                    }
                                }
                            },
                            changeItem = { cartId, result ->
                                val newName = item.name
                                val newImage = item.image
                                val newCategory = item.category
                                val newPrice = item.price
                                val newBrand = item.brand

                                shoppingCartViewModel.update(
                                    name = newName,
                                    image = newImage,
                                    category = newCategory,
                                    price = newPrice,
                                    brand = newBrand,
                                    orderQuantity = result,
                                    username = "eray.kstrl",
                                    cart_id = cartId,
                                )
                            }
                        )
                    }
                }
                if(!allList.value.isNullOrEmpty()) {
                    CustomOrderButton(
                        price = totalOrderPrice.value
                    )
                    HorizontalDivider(
                        thickness = 2.dp,
                        color = colorResource(id = R.color.card_background_color)
                    )
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
    }
}