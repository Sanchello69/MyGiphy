package com.vas.mygiphy.presentation.detailsGif

import androidx.lifecycle.LiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.vas.mygiphy.data.model.GifData

class DetailsGifViewModel(state: SavedStateHandle) : ViewModel() {
    val detailsGif: LiveData<GifData> = state.getLiveData<GifData>("gif")
}