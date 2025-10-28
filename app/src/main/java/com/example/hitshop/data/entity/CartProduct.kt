package com.example.hitshop.data.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

data class CartProduct(

    @SerializedName("sepetId")

    var cart_id: Int,

    @SerializedName("ad")
    var name: String,

    @SerializedName("resim")
    var image: String,

    @SerializedName("kategori")
    var category: String,

    @SerializedName("fiyat")
    var price: Int,

    @SerializedName("marka")
    var brand: String,

    @SerializedName("siparisAdeti")
    var order_quantity: Int,

    @SerializedName("kullaniciAdi")
    val username: String
)
