package com.example.hitshop.ui.components

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import com.example.hitshop.R

@Composable
fun CustomOutlinedTextField(
    hint : String,
    value : String,
    onValueChange : (String) -> Unit,
) {
    OutlinedTextField(
        modifier = Modifier
            .fillMaxWidth()
        ,
        value = value,
        onValueChange = {
            onValueChange(it)
        },
        label = {
            Text(
                text = hint,
                color = colorResource(id = R.color.white_black)
            )
        },
        colors = OutlinedTextFieldDefaults.colors(
            focusedBorderColor = colorResource(id = R.color.white_black),
            unfocusedBorderColor = colorResource(id = R.color.white_black),
            focusedTextColor = colorResource(id = R.color.white_black),
            unfocusedTextColor = colorResource(id = R.color.white_black),
            cursorColor = colorResource(id = R.color.white_black),
            errorContainerColor = colorResource(id = R.color.background_color),
            focusedContainerColor = colorResource(id = R.color.background_color),
            unfocusedContainerColor = colorResource(id = R.color.background_color),
            disabledContainerColor = colorResource(id = R.color.background_color)
        ),
        leadingIcon = {
            Icon(
                Icons.Filled.Search,
                tint = colorResource(id = R.color.product_text_color),
                contentDescription = stringResource(id = R.string.search_product_text)
            )
        }
    )
}