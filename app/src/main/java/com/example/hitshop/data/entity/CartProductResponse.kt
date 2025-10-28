package com.example.hitshop.data.entity

import com.google.gson.annotations.SerializedName

class CartProductResponse(
    @SerializedName("urunler_sepeti")
    var cart_product:  List<CartProduct>,

    @SerializedName("success")
    var success : Int
) {
}