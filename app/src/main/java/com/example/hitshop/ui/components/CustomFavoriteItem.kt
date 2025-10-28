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
<<<<<<< HEAD
import androidx.compose.material.icons.filled.FavoriteBorder
=======
>>>>>>> d9255f8 (MainViewmodel has uploaded for improving UI)
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.skydoves.landscapist.glide.GlideImage
import com.example.hitshop.R
import com.example.hitshop.data.entity.Favorite

@Composable
fun CustomFavoriteItem(
    favorite : Favorite ,
    onItemClick : () -> Unit,
    deleteFromFavorite : () -> Unit
) {
    val url = "http://kasimadalan.pe.hu/urunler/resimler/${favorite.image}"

    Card(
        modifier = Modifier
            .fillMaxSize(),
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
                    text = favorite.brand,
                    fontWeight = FontWeight.Bold,
                    color = colorResource(id = R.color.black)
                )
                Spacer(modifier = Modifier.height(8.dp))

                Text(
                    text = favorite.name
                )

                Spacer(
                    modifier = Modifier
                        .height(16.dp)
                )
                Text(
                    text = favorite.price.toString() + "₺",
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
                    deleteFromFavorite()
                }
            ) {
                Icon(
                    Icons.Filled.Favorite,
                    contentDescription = stringResource(id = R.string.add_to_favorite),
                    tint = colorResource(id = R.color.top_color)
                )
            }
        }
    }
}