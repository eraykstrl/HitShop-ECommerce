package com.example.hitshop.retrofit

import com.example.hitshop.data.entity.CRUDResponse
import com.example.hitshop.data.entity.CartProductResponse
import com.example.hitshop.data.entity.ProductResponse
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.POST


interface HitShopDao {

    @GET("urunler/tumUrunleriGetir.php")
    suspend fun getAllProducts() : ProductResponse

    @POST("urunler/sepeteUrunEkle.php")
    @FormUrlEncoded
    suspend fun insertProductToCart(
        @Field ("ad") name : String,

        @Field ("resim") image : String,

        @Field ("kategori") category : String,

        @Field ("fiyat") price : Int,

        @Field ("marka") brand : String,

        @Field ("siparisAdeti") orderQuantity : Int,

        @Field ("kullaniciAdi") username : String
    ) : CRUDResponse


    @POST("urunler/sepettekiUrunleriGetir.php")
    @FormUrlEncoded
    suspend fun getAllCart(
        @Field("kullaniciAdi") username: String
    ) : CartProductResponse

    @POST("urunler/sepettenUrunSil.php")
    @FormUrlEncoded
    suspend fun delete(
        @Field("sepetId") cart_id : Int,
        @Field("kullaniciAdi") username : String
    ) : CRUDResponse

}