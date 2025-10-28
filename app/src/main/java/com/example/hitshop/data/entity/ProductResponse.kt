package com.example.hitshop.data.entity

import com.google.gson.annotations.SerializedName

class ProductResponse(

    @SerializedName("urunler")
    var product : List<Product>,

    @SerializedName("success")
    var success : Int
) {
}