package com.vas.mygiphy.di

import com.vas.mygiphy.presentation.listGif.GifsFragment
import dagger.Component

@Component(modules = [DataModule::class])
interface AppComponent {
    fun inject(gifsFragment: GifsFragment)
}