package com.example.hitshop.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
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
import com.example.hitshop.data.entity.Product
import com.skydoves.landscapist.glide.GlideImage

@Composable
fun CustomDetailProductItem(
    product: Product,
    quantity : (Int) -> Unit,
) {

    val quantityProduct = remember { mutableStateOf(0) }

    Text(
        text = product.category,
        fontWeight = FontWeight.Bold,
        fontSize = 24.sp,
        color = colorResource(id = R.color.detail_screen_text)
    )
    Spacer(modifier = Modifier.size(16.dp))
    val url = "http://kasimadalan.pe.hu/urunler/resimler/${product.image}"
    GlideImage(
        imageModel = url,
        modifier = Modifier
            .fillMaxWidth()
            .height(300.dp)
    )

    Spacer(modifier = Modifier.size(16.dp))

    Text(
        text = product.brand,
        fontWeight = FontWeight.Bold,
        color = colorResource(id = R.color.detail_screen_text),
        fontSize = 32.sp
    )


    Spacer(modifier = Modifier
        .size(8.dp))

    Text(
        text = product.name,
        fontWeight = FontWeight.Bold,
        fontSize = 24.sp,
        color = colorResource(id = R.color.detail_screen_text)
    )

    Spacer(modifier = Modifier
        .size(16.dp))

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
                if(quantityProduct.value > 0) {
                    quantityProduct.value --
                    quantity(quantityProduct.value)
                }
            }
        ) {
            Icon(
                painterResource(id = R.drawable.reduce_icon),
                contentDescription = stringResource(id = R.string.quantity_reduce),
                tint = colorResource(id = R.color.black)
            )
        }

        Text(
            text = quantityProduct.value.toString(),
            color = colorResource(id = R.color.product_text_color)
        )
        IconButton(
            onClick = {
                quantityProduct.value ++
                quantity(quantityProduct.value)
            }
        ) {
            Icon(
                painterResource(id = R.drawable.increase_icon),
                contentDescription = stringResource(id = R.string.quantity_increase),
                tint = colorResource(id = R.color.black)

            )
        }

    }

}