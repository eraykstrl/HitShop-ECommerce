package com.example.hitshop.data.repos

import com.example.hitshop.data.datasources.HitShopDatasource
import com.example.hitshop.data.entity.CartProduct
import com.example.hitshop.data.entity.Favorite
import com.example.hitshop.data.entity.Product
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class HitShopRepository(
    var hitShopDatasource: HitShopDatasource
) {

    suspend fun getAllProducts() : List<Product> = hitShopDatasource.getAllProducts()

    suspend fun insertProductToCart(
        name : String,
        image : String,
        category : String,
        price : Int,
        brand : String,
        orderQuantity : Int,
        username : String
    ) {
        hitShopDatasource.insertProductToCart(
            name = name,
            image = image,
            category = category,
            price = price,
            brand = brand,
            orderQuantity = orderQuantity,
            username = username
        )
    }

    suspend fun getAllCart(username : String) : List<CartProduct> = hitShopDatasource.getAllCart(username = username)

    suspend fun deleteFromCart(cart_id : Int,username: String) = hitShopDatasource.deleteFromCard(cart_id,username)

    suspend fun changeNumberByName(
        name: String,
        image: String,
        category: String,
        price: Int,
        brand: String,
        orderQuantity: Int,
        username: String
    ) {
        hitShopDatasource.changeNumberByName(
            name = name,
            image = image,
            category = category,
            price = price,
            brand = brand,
            orderQuantity = orderQuantity,
            username = username
        )
    }

    suspend fun insertToFavorites(
        id : Int,
        name : String,
        image : String,
        category : String,
        price : Int,
        brand : String,
    ) {
        hitShopDatasource.insertToFavorites(
                id = id,
                name = name,
                image = image,
                category = category,
                price = price,
                brand = brand
        )
    }

    suspend fun deleteFavorites(favorite_id : Int) {
        hitShopDatasource.deleteFavorites(favorite_id = favorite_id)
    }

    suspend fun getAllFavorites() : List<Favorite> = hitShopDatasource.getAllFavorites()

    suspend fun insertProductToRoom(productList : List<Product>) {
        hitShopDatasource.insertProductToRoom(
            productList = productList
        )
    }

    suspend fun searchProduct(searchText : String) : List<Product> = hitShopDatasource.searchProduct(searchText = searchText)

}