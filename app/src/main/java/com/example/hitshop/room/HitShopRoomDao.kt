package com.example.hitshop.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.hitshop.data.entity.Favorite
import com.example.hitshop.data.entity.Product

@Dao
interface HitShopRoomDao {

    @Insert
    suspend fun insertFavorite(favorites: Favorite)

    @Query("DELETE FROM favorites WHERE favorite_id = :favorite_id")
    suspend fun deleteFavorite(favorite_id : Int)

    @Query("SELECT * FROM favorites")
    suspend fun getAllFavorites(): List<Favorite>

    @Query("SELECT EXISTS(SELECT 1 FROM favorites WHERE id = :id)")
    suspend fun isInFavorites(id : Int) : Boolean?

    @Query("SELECT * FROM favorites WHERE ad LIKE '%' || :text || '%' ")
    suspend fun search(text : String) : List<Favorite>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertProductToRoom(products: List<Product>)

    @Query("SELECT * FROM products WHERE " +
            "(ad LIKE '%' || :searchText || '%') OR " +
            "(marka LIKE '%' || :searchText || '%') OR " +
            "(kategori LIKE '%' || :searchText || '%')")

    suspend fun searchProduct(searchText: String): List<Product>
}