package com.example.hitshop.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.hitshop.R
import com.example.hitshop.data.entity.CartProduct
import com.skydoves.landscapist.glide.GlideImage

@Composable
fun CustomShoppingCartItem(
    product : CartProduct,
    snackbarItem : () -> Unit,
    changeItem : (Int, Int) -> Unit,
) {
    val quantity = remember(product.order_quantity) {
        mutableStateOf(product.order_quantity)
    }

    val totalCartPrice = product.price * product.order_quantity
    val url = "http://kasimadalan.pe.hu/urunler/resimler/${product.image}"


    Card(
        modifier = Modifier
            .fillMaxSize()
            .padding(vertical = 16.dp, horizontal = 16.dp),
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(
            containerColor = colorResource(id = R.color.card_background_color)
        ),
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(all = 8.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = product.category,
                fontWeight = FontWeight.Bold,
                color = colorResource(id = R.color.product_text_color),
                fontSize = 24.sp
            )
            Spacer(modifier = Modifier.height(16.dp))
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 8.dp)
                    .height(200.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {

                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    Text(
                        text = product.brand,
                        fontWeight = FontWeight.Bold,
                        color = colorResource(id = R.color.product_text_color),
                        fontSize = 24.sp
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(
                        text = product.name,
                        fontWeight = FontWeight.Bold,
                        color = colorResource(id = R.color.black),
                        fontSize = 16.sp
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    Column(
                        modifier = Modifier
                            .size(220.dp,120.dp)
                            .background(color = colorResource(id = R.color.white), shape = RoundedCornerShape(12.dp))
                            .border(
                                width = 2.dp,
                                color = colorResource(id = R.color.product_text_color),
                                shape = RoundedCornerShape(12.dp)
                            ),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center
                    ) {
                        GlideImage(
                            imageModel = url,
                            modifier = Modifier
                                .fillMaxSize()
                        )
                    }
                }
                Column(

                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Spacer(modifier = Modifier.height(8.dp))
                    IconButton(
                        onClick = {
                            snackbarItem()
                        }
                    ) {
                        Icon(
                            Icons.Filled.Delete,
                            contentDescription = stringResource(id = R.string.delete_from_cart),
                            tint = colorResource(id = R.color.product_text_color)
                        )
                    }

                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier
                            .background(color = colorResource(id = R.color.white), shape = RoundedCornerShape(12.dp))
                            .border(
                                width = 2.dp,
                                color = colorResource(id = R.color.product_text_color),
                                shape = RoundedCornerShape(12.dp)
                            )
                    ) {
                        IconButton(
                            onClick = {
                                if(quantity.value == 1) {
                                    snackbarItem()
                                }
                                else
                                {
                                    quantity.value --
                                    changeItem(product.cart_id,quantity.value)
                                }
                            }
                        ) {
                            Icon(
                                painterResource(id = R.drawable.reduce_icon),
                                contentDescription = stringResource(id = R.string.quantity_reduce),
                                tint = colorResource(id = R.color.card_background_color)
                            )
                        }

                        Text(
                            text = quantity.value.toString(),
                            color = colorResource(id = R.color.product_text_color)
                        )
                        IconButton(
                            onClick = {
                                quantity.value ++
                                changeItem(product.cart_id,quantity.value)
                            }
                        ) {
                            Icon(
                                painterResource(id = R.drawable.increase_icon),
                                contentDescription = stringResource(id = R.string.quantity_increase),
                                tint = colorResource(id = R.color.card_background_color)

                            )
                        }

                    }
                }
            }
            Spacer(modifier = Modifier.size(16.dp))
            Column(
                modifier = Modifier
                    .background(color = colorResource(id = R.color.white), shape = RoundedCornerShape(12.dp))
                    .border(
                        width = 2.dp,
                        color = colorResource(id = R.color.product_text_color),
                        shape = RoundedCornerShape(12.dp)
                    )

            ) {
                Text(
                    modifier = Modifier
                        .padding(all = 8.dp),
                    text = totalCartPrice.toString() + "₺",
                    color = colorResource(id = R.color.product_text_color),
                    fontWeight = FontWeight.Bold,
                    fontSize = 24.sp
                )
            }
        }

    }
}