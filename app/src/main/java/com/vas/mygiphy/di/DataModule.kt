package com.vas.mygiphy.di

import com.vas.mygiphy.data.network.GifApi
import com.vas.mygiphy.utils.Constants.BASE_URL
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@Module
class DataModule{

    @Provides
    fun provideClient(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Provides
    fun provideGifApi(retrofit: Retrofit): GifApi {
        return retrofit.create(GifApi::class.java)
    }

}