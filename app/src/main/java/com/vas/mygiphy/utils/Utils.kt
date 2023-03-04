package com.vas.mygiphy.utils

import android.widget.Button
import android.widget.ImageView
import android.widget.ProgressBar
import androidx.core.view.isVisible
import coil.load

fun ImageView.loadGif(
    url: String,
    progressBar: ProgressBar?,
    buttonError: Button?
) {
    load(url) {
        crossfade(true)
        listener(
            onStart = {
                progressBar?.isVisible = true
                buttonError?.isVisible = false
            },
            onSuccess = { _, _ ->
                progressBar?.isVisible = false
                buttonError?.isVisible = false
            },
            onError = { _, _ ->
                progressBar?.isVisible = false
                buttonError?.isVisible = true
            }
        )
    }
}