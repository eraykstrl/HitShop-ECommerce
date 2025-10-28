package com.example.hitshop.data.datasources

import android.util.Log
import com.example.hitshop.data.entity.CartProduct
import com.example.hitshop.data.entity.Favorite
import com.example.hitshop.data.entity.Product
import com.example.hitshop.retrofit.HitShopDao
import com.example.hitshop.room.HitShopRoomDao
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.withContext
import java.lang.Exception

class HitShopDatasource(
    var hitShopDao: HitShopDao,
    var hitShopRoomDao: HitShopRoomDao
) {

    suspend fun getAllProducts() : List<Product> = withContext(Dispatchers.IO) {
        try {
            return@withContext hitShopDao.getAllProducts().product
        }
        catch (e : Exception) {
            return@withContext emptyList<Product>()
        }
    }

    suspend fun insertProductToCart(
        name : String,
        image : String,
        category : String,
        price : Int,
        brand : String,
        orderQuantity : Int,
        username : String
    ) {
        hitShopDao.insertProductToCart(
            name = name,
            image = image,
            category = category,
            price = price,
            brand = brand,
            orderQuantity = orderQuantity,
            username = username
        )
    }

    suspend fun getAllCart(username: String) : List<CartProduct> = withContext(Dispatchers.IO) {
        try {
            return@withContext hitShopDao.getAllCart(username = username).cart_product
        }
        catch (e : Exception) {
            return@withContext emptyList<CartProduct>()
        }
    }

    suspend fun deleteFromCard(cart_id : Int,username : String) {
        hitShopDao.delete(cart_id,username)

    }
    suspend fun changeNumberByName(
        name: String,
        image: String,
        category: String,
        price: Int,
        brand: String,
        orderQuantity: Int,
        username: String
    ) = withContext(Dispatchers.IO) {
        try {
            val cartList = hitShopDao.getAllCart(username).cart_product
            cartList.filter { it.name == name }.forEach { product ->
                Log.e("ChangeNumber", "Siliniyor: cart_id=${product.cart_id}, adet=${product.order_quantity}")
                hitShopDao.delete(product.cart_id, username)
            }
            delay(300)
            Log.e("ChangeNumber", "Ekleniyor: name=$name, adet=$orderQuantity")
            hitShopDao.insertProductToCart(
                name = name,
                image = image,
                category = category,
                price = price,
                brand = brand,
                orderQuantity = orderQuantity,
                username = username
            )

        } catch (e: Exception) {
            Log.e("ChangeNumber", "HATA: ${e.message}")
        }
    }


    suspend fun insertToFavorites(
        id : Int,
        name : String,
        image : String,
        category : String,
        price : Int,
        brand : String,
    ) {
        hitShopRoomDao.insertFavorite(
            Favorite(
                favorite_id = 0,
                id = id,
                name = name,
                image = image,
                category = category,
                price = price,
                brand = brand
            )
        )
    }

    suspend fun deleteFavorites(favorite_id : Int) {
        hitShopRoomDao.deleteFavorite(favorite_id = favorite_id)
    }

    suspend fun getAllFavorites() : List<Favorite>  = withContext(Dispatchers.IO){
        try {
            return@withContext hitShopRoomDao.getAllFavorites()
        }
        catch (e : kotlin.Exception) {
            return@withContext emptyList<Favorite>()
        }
    }

    suspend fun insertProductToRoom(productList : List<Product>) {
        hitShopRoomDao.insertProductToRoom(
            products = productList
        )
    }

    suspend fun searchProduct(searchText : String) : List<Product> = withContext(Dispatchers.IO) {
        try {
            return@withContext hitShopRoomDao.searchProduct(searchText = searchText)
        }
        catch (e : kotlin.Exception) {
            return@withContext emptyList()
        }
    }

}