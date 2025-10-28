package com.example.hitshop.ui.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
<<<<<<< HEAD
import androidx.compose.material.icons.filled.FavoriteBorder
=======
>>>>>>> d9255f8 (MainViewmodel has uploaded for improving UI)
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.hitshop.R

@Composable
fun CustomMainBottomAppBar(
    onClickItem : (Int) -> Unit,
    screen : Int
) {
    val selectedScreen = remember { mutableStateOf(screen) }
    val context = LocalContext.current
    val homeColor = remember { mutableStateOf(context.getColor(R.color.black)) }
    val cartColor = remember { mutableStateOf(context.getColor(R.color.white)) }
    val favoriteColor = remember { mutableStateOf(context.getColor(R.color.white)) }

    BottomAppBar(
        contentColor = colorResource(id = R.color.white),
        containerColor = colorResource(id = R.color.bottom_nav_color),
        modifier = Modifier
            .fillMaxWidth()
            .height(100.dp)
    ) {
        if(selectedScreen.value == 0) {
            homeColor.value = context.getColor(R.color.black)
        }
        else
        {
            homeColor.value = context.getColor(R.color.white)
        }

        if(selectedScreen.value == 1) {
            cartColor.value = context.getColor(R.color.black)
        }
        else
        {
            cartColor.value = context.getColor(R.color.white)
        }

        if(selectedScreen.value == 2) {
            favoriteColor.value = context.getColor(R.color.black)
        }
        else
        {
            favoriteColor.value = context.getColor(R.color.white)
        }


        NavigationBarItem(
            selected = selectedScreen.value == 0,
            onClick = {
                selectedScreen.value = 0
                onClickItem(selectedScreen.value)
            },
            icon = {
                Icon(
                    Icons.Filled.Home,
                    contentDescription = stringResource(id = R.string.go_main_screen),
                    tint = colorResource(id = R.color.white)
                )
            },
            label = {
                Text(
                    text = stringResource(id = R.string.home_screen_text),
                    color = Color(homeColor.value)
                )
            },
            colors = NavigationBarItemDefaults.colors(
                indicatorColor = colorResource(id = R.color.black)
            )
        )
        NavigationBarItem(
            selected = selectedScreen.value == 1,
            onClick = {
                selectedScreen.value = 1
                onClickItem(selectedScreen.value)
            },
            icon = {
                Icon(
                    Icons.Filled.ShoppingCart,
                    contentDescription = stringResource(id = R.string.go_cart_screen),
                    tint = colorResource(id = R.color.white)
                )
            },
            label = {
                Text(
                    text = stringResource(id = R.string.mycart_screen_text),
                    color = Color(cartColor.value)

                )
            },
            colors = NavigationBarItemDefaults.colors(
                indicatorColor = colorResource(id = R.color.black)
            )
        )

        NavigationBarItem(
            selected = selectedScreen.value == 2,
            onClick = {
                selectedScreen.value = 2
                onClickItem(selectedScreen.value)
            },
            icon = {
                Icon(
                    Icons.Filled.Favorite,
                    contentDescription = stringResource(id = R.string.go_my_favorites),
                    tint = colorResource(id = R.color.white)
                )
            },
            label = {
                Text(
                    text = stringResource(id = R.string.my_favorites),
                    color = Color(favoriteColor.value)

                )
            },
            colors = NavigationBarItemDefaults.colors(
                indicatorColor = colorResource(id = R.color.black)
            )
        )
    }
}