package com.example.hitshop.ui.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.hitshop.data.entity.CartProduct
import com.example.hitshop.data.repos.HitShopRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ShoppingCartViewModel @Inject constructor(
    var hitShopRepository: HitShopRepository
) : ViewModel() {

    val productCart = MutableLiveData<List<CartProduct>>()
    val isLoading = MutableLiveData<Boolean>()


    init {
        isLoading.value = false
        getAllCart("eray.kstrl")
    }

    fun getAllCart(username : String) {
        CoroutineScope(Dispatchers.Main).launch {
            productCart.value = hitShopRepository.getAllCart(username = username)
        }
    }

    fun deleteFromCart(cart_id : Int,username: String) {
        CoroutineScope(Dispatchers.Main).launch {
            isLoading.value = true
            hitShopRepository.deleteFromCart(cart_id,username)
            isLoading.value = false
            getAllCart(username = username)
        }
    }

    fun update(
        cart_id: Int,
        name: String,
        image: String,
        category: String,
        price: Int,
        brand: String,
        orderQuantity: Int,
        username: String,
    ) {
        isLoading.value = true
        CoroutineScope(Dispatchers.Main).launch {
            hitShopRepository.changeNumberByName(
                name = name,
                image = image,
                category = category,
                price = price,
                brand = brand,
                orderQuantity = orderQuantity,
                username = username
            )
            getAllCart(username = "eray.kstrl")
            isLoading.value = false

        }
    }

}