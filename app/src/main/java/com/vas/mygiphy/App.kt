package com.vas.mygiphy

import android.app.Application
import android.content.Context
import android.os.Build.VERSION.SDK_INT
import coil.ImageLoader
import coil.ImageLoaderFactory
import coil.decode.GifDecoder
import coil.decode.ImageDecoderDecoder
import com.vas.mygiphy.di.AppComponent
import com.vas.mygiphy.di.DaggerAppComponent

class App : Application(), ImageLoaderFactory {

    lateinit var appComponent: AppComponent

    override fun onCreate() {
        super.onCreate()
        appComponent = DaggerAppComponent.create()
    }

    override fun newImageLoader(): ImageLoader {
        return ImageLoader.Builder(this)
            .components {
                if (SDK_INT >= 28) {
                    add(ImageDecoderDecoder.Factory())
                } else {
                    add(GifDecoder.Factory())
                }
            }
            .build()
    }

}

val Context.appComponent: AppComponent
    get() = when(this){
        is App -> appComponent
        else -> applicationContext.appComponent
    }