package com.example.hitshop.data.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "products")
data class Product(

    @PrimaryKey
    @SerializedName("id")
    @ColumnInfo(name = "id")
    val id : Int,

    @SerializedName("ad")
    @ColumnInfo(name = "ad")
    val name : String,

    @SerializedName("resim")
    @ColumnInfo(name = "resim")
    val image : String,

    @SerializedName("kategori")
    @ColumnInfo(name = "kategori")
    val category : String,

    @SerializedName("fiyat")
    @ColumnInfo(name = "fiyat")
    val price : Int,

    @SerializedName("marka")
    @ColumnInfo(name = "marka")
    val brand : String
) {
}