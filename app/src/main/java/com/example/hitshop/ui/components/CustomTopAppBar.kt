package com.example.hitshop.ui.components

import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import com.example.hitshop.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CustomTopAppBar(screen : String) {
    CenterAlignedTopAppBar(
        title = {
            Text(
                text = screen,
                fontWeight = FontWeight.Bold,
                color = colorResource(id = R.color.white)
            )
        },
        colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
            containerColor = colorResource(id = R.color.top_color)
        )
    )
}