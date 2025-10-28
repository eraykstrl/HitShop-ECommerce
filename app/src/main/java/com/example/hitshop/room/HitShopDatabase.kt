package com.example.hitshop.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.hitshop.data.entity.Favorite
import com.example.hitshop.data.entity.Product

@Database(entities = [Favorite::class, Product::class], version = 2)
abstract class HitShopDatabase : RoomDatabase() {
    abstract fun hitShopRoomDao() : HitShopRoomDao

}