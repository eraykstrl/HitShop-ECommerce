package com.example.hitshop.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.hitshop.R
import com.example.hitshop.data.entity.Product

@Composable
fun CustomBottomAppBar(
    product: Product,
    addToCart : () -> Unit
) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(color = colorResource(id = R.color.bottom_nav_color))
                .padding(horizontal = 16.dp, vertical = 16.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {

            Button(
                onClick = {

                },
                modifier = Modifier
                    .clip(RoundedCornerShape(12.dp))
                    .background(
                        color = colorResource(id = R.color.white),
                        shape = RoundedCornerShape(12.dp)),
                colors = ButtonDefaults.buttonColors(
                    containerColor = colorResource(id = R.color.white),
                    contentColor = colorResource(id = R.color.product_text_color)
                )
            ) {
                Text(
                    text = product.price.toString() + "₺",
                    fontWeight = FontWeight.Bold,
                    color = colorResource(id = R.color.product_text_color),
                    fontSize = 24.sp
                )
            }

            Button(
                onClick = {
                    addToCart()
                },
                modifier = Modifier
                    .clip(RoundedCornerShape(12.dp))
                    .background(
                        color = colorResource(id = R.color.white),
                        shape = RoundedCornerShape(12.dp))
                ,
                colors = ButtonDefaults.buttonColors(
                    contentColor = colorResource(id = R.color.product_text_color),
                    containerColor = colorResource(id = R.color.white)
                )
            ) {
                Text(
                    text = stringResource(id = R.string.add_to_card),
                    fontWeight = FontWeight.Bold,
                    color = colorResource(id = R.color.product_text_color),
                    fontSize = 24.sp
                )
            }
        }
}