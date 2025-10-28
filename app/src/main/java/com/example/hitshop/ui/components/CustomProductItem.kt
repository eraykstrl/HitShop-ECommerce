package com.example.hitshop.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.hitshop.data.entity.Product
import com.skydoves.landscapist.glide.GlideImage
import com.example.hitshop.R

@Composable
fun CustomProductItem(
    product: Product ,
    onItemClick : () -> Unit,
    addToFavorite : () -> Unit,
    favorite : Boolean ?
) {
    val url = "http://kasimadalan.pe.hu/urunler/resimler/${product.image}"

    Card(
        modifier = Modifier
            .fillMaxSize()
            .background(
                color = colorResource(id = R.color.card_background_color),
                shape = RoundedCornerShape(12.dp)
            )
        ,
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(
            containerColor = colorResource(id = R.color.card_background_color)
        ),
        onClick = {
            onItemClick()
        }
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(top = 8.dp),
                verticalArrangement = Arrangement.Top,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Column(
                    modifier = Modifier
                        .size(180.dp,220.dp)
                        .background(color = colorResource(id = R.color.white), shape = RoundedCornerShape(12.dp))
                        .border(
                            width = 2.dp,
                            color = colorResource(id = R.color.product_text_color),
                            shape = RoundedCornerShape(12.dp)
                        ),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    GlideImage(
                        imageModel = url,
                        modifier = Modifier
                            .size(180.dp,180.dp)
                    )
                }
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = product.brand,
                    fontWeight = FontWeight.Bold,
                    color = colorResource(id = R.color.black)
                )
                Spacer(modifier = Modifier.height(8.dp))

                Text(
                    text = product.name
                )

                Spacer(
                    modifier = Modifier
                        .height(16.dp)
                )
                Text(
                    text = product.price.toString() + "₺",
                    color = colorResource(id = R.color.product_text_color),
                    fontWeight = FontWeight.Bold,
                    fontSize = 24.sp
                )
                Spacer(
                    modifier = Modifier
                        .height(8.dp)
                )
            }


            IconButton(
                modifier = Modifier
                    .align(Alignment.TopEnd)
                    .padding(8.dp),
                onClick = {
                    addToFavorite()
                }
            ) {
                if(favorite == true) {
                    Icon(
                        Icons.Filled.Favorite,
                        contentDescription = stringResource(id = R.string.add_to_favorite),
                        tint = colorResource(id = R.color.top_color)
                    )
                }
                else {
                    Icon(
                        Icons.Filled.FavoriteBorder,
                        contentDescription = stringResource(id = R.string.add_to_favorite),
                        tint = colorResource(id = R.color.top_color)
                    )
                }
            }
        }
    }
}