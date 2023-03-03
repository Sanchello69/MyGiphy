package com.vas.mygiphy.data.pagingSource

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.vas.mygiphy.data.model.GifData
import com.vas.mygiphy.data.network.GifApi

const val NETWORK_PAGE_SIZE = 25

class GifsPagingSource (
    private val api: GifApi,
    private val query: String
) : PagingSource<Int, GifData>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, GifData> {

        return try {
            val offset: Int = params.key ?: 0
            val pageSize: Int = params.loadSize.coerceAtMost(NETWORK_PAGE_SIZE)

            val response =
                if (query.isNotEmpty()) api.getGifs(query = query, offset = offset)
                else api.getTrendingGifs(offset = offset)
            val gifs = response.data

            val nextKey = if (gifs.size < pageSize) null else offset + 25
            val prevKey = if (offset == 0) null else offset - 25

            LoadResult.Page(gifs, prevKey, nextKey)

        } catch (e: Exception){
            LoadResult.Error(e)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, GifData>): Int? {
        return null
    }
}