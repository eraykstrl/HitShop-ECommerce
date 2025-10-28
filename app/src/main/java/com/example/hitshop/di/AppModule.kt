package com.example.hitshop.di

import android.content.Context
import androidx.room.Room
import com.example.hitshop.data.datasources.HitShopDatasource
import com.example.hitshop.data.repos.HitShopRepository
import com.example.hitshop.retrofit.HitShopDao
import com.example.hitshop.room.HitShopDatabase
import com.example.hitshop.room.HitShopRoomDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AppModule {

    @Provides
    @Singleton
    fun provideHitShopRepository(hitShopDatasource: HitShopDatasource) : HitShopRepository {
        return HitShopRepository(hitShopDatasource)
    }

    @Provides
    @Singleton
    fun provideHitShopDatasource(hitShopDao: HitShopDao,hitShopRoomDao: HitShopRoomDao) : HitShopDatasource {
        return HitShopDatasource(hitShopDao,hitShopRoomDao)
    }

    @Provides
    @Singleton
    fun provideHitShopDao(retrofit: Retrofit) : HitShopDao {
        return retrofit.create(HitShopDao::class.java)
    }

    @Provides
    @Singleton
    fun provideRetrofit() : Retrofit {
        return Retrofit
            .Builder()
            .baseUrl("http://kasimadalan.pe.hu/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }


    @Provides
    @Singleton
    fun provideToDosDao(@ApplicationContext context : Context) : HitShopRoomDao {
        val db = Room
            .databaseBuilder(
                context,
                HitShopDatabase::class.java,
                "hitshop_db.sqlite"
            )
            .createFromAsset("hitshop_db.sqlite")
            .build()

        return db.hitShopRoomDao()
    }

}