package com.vas.mygiphy.data

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.vas.mygiphy.data.model.GifData
import com.vas.mygiphy.data.network.GifApi
import com.vas.mygiphy.data.pagingSource.GifsPagingSource
import com.vas.mygiphy.data.pagingSource.NETWORK_PAGE_SIZE
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GifRepository @Inject constructor(private val api: GifApi) {

    private var query: String = ""
        set(value) {
            field = value
            pagingSource = GifsPagingSource(api = api, query = field)
        }
    private var pagingSource: GifsPagingSource = GifsPagingSource(api = api, query = query)

    fun getGifsFlow() : Flow<PagingData<GifData>> {
        return Pager(
            config = PagingConfig(
                pageSize = NETWORK_PAGE_SIZE,
                enablePlaceholders = false
            ),
            pagingSourceFactory = {
                pagingSource
            }
        ).flow
    }

    fun searchGifs(query: String){
        pagingSource.invalidate()
        this.query = query
    }

}