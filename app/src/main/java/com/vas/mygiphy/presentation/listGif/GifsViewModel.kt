package com.vas.mygiphy.presentation.listGif

import android.util.Log
import androidx.lifecycle.*
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.vas.mygiphy.data.GifRepository
import com.vas.mygiphy.data.model.GifData
import kotlinx.coroutines.*
import kotlinx.coroutines.channels.BroadcastChannel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.*
import javax.inject.Inject

@OptIn(FlowPreview::class)
class GifsViewModel @Inject constructor(private val repository: GifRepository) : ViewModel() {

    private val querySearch = MutableSharedFlow<String>()

    val gifListData: Flow<PagingData<GifData>> = repository
        .getGifsFlow()
        .cachedIn(viewModelScope)

    init {
        collectQuerySearch()
    }

    fun updateQuerySearch(query: String){
        viewModelScope.launch {
            querySearch.emit(query)
        }
    }

    private fun collectQuerySearch(){
        viewModelScope.launch {
            querySearch
                .debounce(500)
                .distinctUntilChanged()
                .collect{ repository.searchGifs(it) }
        }
    }

}