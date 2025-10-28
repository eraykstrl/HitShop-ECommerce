package com.example.hitshop.data.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "favorites")
data class Favorite(


    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "favorite_id")
    var favorite_id : Int,

    @ColumnInfo(name = "id")
    var id: Int ,

    @ColumnInfo(name = "ad")
    var name: String,

    @ColumnInfo(name = "resim")
    var image: String ,

    @ColumnInfo(name = "kategori")
    var category: String ,

    @ColumnInfo(name = "fiyat")
    var price: Int ,

    @ColumnInfo(name = "marka")
    var brand: String ,
) {

}