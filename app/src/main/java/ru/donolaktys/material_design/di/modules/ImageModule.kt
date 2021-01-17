package ru.donolaktys.material_design.di.modules

import android.webkit.WebView
import dagger.Module
import dagger.Provides
import ru.donolaktys.material_design.mvp.model.image.IImageLoader
import ru.donolaktys.material_design.ui.image.CoilLoader
import javax.inject.Singleton

@Module
class ImageModule {
    @Singleton
    @Provides
    fun loadImage() : IImageLoader<WebView> = CoilLoader()
}