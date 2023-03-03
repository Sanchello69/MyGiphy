package com.vas.mygiphy.presentation.listGif

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.vas.mygiphy.data.GifRepository
import javax.inject.Inject

class GifsViewModelFactory @Inject constructor(
    private val repository: GifRepository
) : ViewModelProvider.Factory  {

    @Suppress("unchecked_cast")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        require(modelClass == GifsViewModel::class.java)
        return GifsViewModel(repository) as T
    }
}