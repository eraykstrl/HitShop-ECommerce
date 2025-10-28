package com.example.hitshop.ui.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.hitshop.data.entity.Favorite
import com.example.hitshop.data.entity.Product
import com.example.hitshop.data.repos.HitShopRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class MainViewModel @Inject constructor(
    var hitShopRepository: HitShopRepository
) : ViewModel() {

    val productLiveData = MutableLiveData<List<Product>>()
    val favoriteLiveData = MutableLiveData<List<Favorite>>()
    val isLoadingLiveData = MutableLiveData<Boolean>()

    init {
        getAllProducts()
        getAllFavorites()
    }

    fun getAllProducts() {
        isLoadingLiveData.value = true
        CoroutineScope(Dispatchers.Main).launch {
            productLiveData.value = hitShopRepository.getAllProducts()
<<<<<<< HEAD
            delay(1000)
=======
>>>>>>> d9255f8 (MainViewmodel has uploaded for improving UI)
            isLoadingLiveData.value = false
        }
    }

    fun insertToFavorites(
        id : Int,
        name : String,
        image : String,
        category : String,
        price : Int,
        brand : String
    ) {
        isLoadingLiveData.value = true
        CoroutineScope(Dispatchers.Main).launch {
            hitShopRepository.insertToFavorites(
                id = id,
                name = name,
                image = image,
                category = category,
                price = price,
                brand = brand
            )
            getAllFavorites()
            isLoadingLiveData.value = false
        }
    }

    fun deleteFavorites(favorite_id : Int) {
        isLoadingLiveData.value = true
        CoroutineScope(Dispatchers.Main).launch {
            hitShopRepository.deleteFavorites(favorite_id = favorite_id)
            getAllFavorites()
<<<<<<< HEAD
            isLoadingLiveData.value = false
=======
>>>>>>> d9255f8 (MainViewmodel has uploaded for improving UI)
        }
    }

    fun getAllFavorites() {
        isLoadingLiveData.value = true
        CoroutineScope(Dispatchers.Main).launch {
            favoriteLiveData.value = hitShopRepository.getAllFavorites()
            isLoadingLiveData.value = false
        }
    }


    fun insertProductToRoom(productList : List<Product>) {
        CoroutineScope(Dispatchers.Main).launch {
            hitShopRepository.insertProductToRoom(
                productList = productList
            )
        }
    }

    fun searchProduct(searchText : String) {
        CoroutineScope(Dispatchers.Main).launch {
            productLiveData.value = hitShopRepository.searchProduct(searchText = searchText)
        }
    }

}