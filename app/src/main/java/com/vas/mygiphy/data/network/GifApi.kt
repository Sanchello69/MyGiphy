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

// можно потом отображать случайную гифку пока запрос пустой
//    @GET("random")
//    suspend fun getRandomGif(
//        @Query("api_key") apiKey: String = "F60MFFt3kDsd6aYb8Pe27VQtP77fKqgL",
//        @Query("tag") tag: String = "",
//        @Query("tag") rating: String = "g"
//    ): ListGifModelData

}