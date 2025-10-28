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
class DetailViewModel @Inject constructor(
    var hitShopRepository: HitShopRepository
) : ViewModel() {

    val cartLiveData = MutableLiveData<List<CartProduct>>()

    fun insertProductToCart(
        name: String,
        image: String,
        category: String,
        price: Int,
        brand: String,
        orderQuantity: Int,
        username: String,
        onComplete : () -> Unit
    ) {
        CoroutineScope(Dispatchers.Main).launch {
            val currentCartList = hitShopRepository.getAllCart(username)

            val existingProduct = currentCartList.find { it.name == name }

            if (existingProduct != null) {
                hitShopRepository.deleteFromCart(
                    username = username,
                    cart_id = existingProduct.cart_id
                )

                val newQuantity = existingProduct.order_quantity + orderQuantity
                hitShopRepository.insertProductToCart(
                    name = name,
                    image = image,
                    category = category,
                    price = price,
                    brand = brand,
                    orderQuantity = newQuantity,
                    username = username
                )
            } else {
                hitShopRepository.insertProductToCart(
                    name = name,
                    image = image,
                    category = category,
                    price = price,
                    brand = brand,
                    orderQuantity = orderQuantity,
                    username = username
                )
            }
            loadAllCartProduct(username)
            onComplete()
        }
    }

    fun loadAllCartProduct(username: String) {
        CoroutineScope(Dispatchers.Main).launch {
            cartLiveData.value = hitShopRepository.getAllCart(username)
        }
    }
}