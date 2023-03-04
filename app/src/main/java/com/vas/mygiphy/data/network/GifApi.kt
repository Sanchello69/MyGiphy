package com.vas.mygiphy.data.network

import com.vas.mygiphy.data.model.GifsModelData
import com.vas.mygiphy.utils.Constants.API_KEY
import retrofit2.http.GET
import retrofit2.http.Query

interface GifApi {

    @GET("search")
    suspend fun getGifs(
        @Query("q") query: String,
        @Query("offset") offset: Int,
        @Query("api_key") apiKey: String = API_KEY,
    ): GifsModelData

    @GET("trending")
    suspend fun getTrendingGifs(
        @Query("offset") offset: Int,
        @Query("api_key") apiKey: String = API_KEY,
    ): GifsModelData

}