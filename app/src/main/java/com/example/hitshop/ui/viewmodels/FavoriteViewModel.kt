package com.example.hitshop.ui.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.hitshop.data.entity.Favorite
import com.example.hitshop.data.repos.HitShopRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FavoriteViewModel @Inject constructor(
    var hitShopRepository: HitShopRepository
) : ViewModel() {

    val favoriteLiveData = MutableLiveData<List<Favorite>>()
    val isLoadingLiveData = MutableLiveData<Boolean>()

    init {
        isLoadingLiveData.value = false
        getAllFavorites()
    }

    fun getAllFavorites() {
        isLoadingLiveData.value = true
        CoroutineScope(Dispatchers.Main).launch {
            favoriteLiveData.value = hitShopRepository.getAllFavorites()
            delay(1000)
            isLoadingLiveData.value = false
        }
    }

    fun deleteFavorites(favorite_id : Int) {
        isLoadingLiveData.value = true
        CoroutineScope(Dispatchers.Main).launch {
            hitShopRepository.deleteFavorites(favorite_id = favorite_id)
            getAllFavorites()
            isLoadingLiveData.value = false
        }
    }
}