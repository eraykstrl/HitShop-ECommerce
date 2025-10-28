package com.example.hitshop.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
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
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.hitshop.R

@Composable
fun CustomOrderButton(
    price : Int
) {

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(color = colorResource(id = R.color.bottom_nav_color))
            .padding(horizontal = 16.dp, vertical = 8.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {

        Button(
            modifier = Modifier
                .background(shape = RoundedCornerShape(12.dp), color = colorResource(id = R.color.white))
                .border(
                    width = 2.dp,
                    color = colorResource(id = R.color.black),
                    shape = RoundedCornerShape(12.dp)
                )
            ,
            colors = ButtonDefaults.buttonColors(
                containerColor = colorResource(id = R.color.white),
                contentColor = colorResource(id = R.color.black)
            )
            ,
            onClick = {

            }
        ) {
            Text(
                text = price.toString() + "₺",
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold
            )
        }

        Button(
            modifier = Modifier
                .background(shape = RoundedCornerShape(12.dp), color = colorResource(id = R.color.white))
                .border(
                    width = 2.dp,
                    color = colorResource(id = R.color.black),
                    shape = RoundedCornerShape(12.dp)
                )
            ,
            colors = ButtonDefaults.buttonColors(
                containerColor = colorResource(id = R.color.white),
                contentColor = colorResource(id = R.color.black)
            )
            ,
            onClick = {

            }
        ) {
            Text(
                text = stringResource(id = R.string.order_button_text),
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold
            )
        }
    }
}