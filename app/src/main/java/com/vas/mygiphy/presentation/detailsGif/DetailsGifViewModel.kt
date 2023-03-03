package com.vas.mygiphy.presentation.detailsGif

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.vas.mygiphy.data.GifRepository
import com.vas.mygiphy.data.model.GifData
import javax.inject.Inject

class DetailsGifViewModel(state: SavedStateHandle) : ViewModel() {
    val detailsGif: LiveData<GifData> = state.getLiveData<GifData>("gif")
}