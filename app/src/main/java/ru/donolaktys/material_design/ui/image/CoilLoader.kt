package ru.donolaktys.material_design.ui.image

import android.webkit.WebView
import coil.Coil
import coil.api.load
import ru.donolaktys.material_design.mvp.model.image.IImageLoader

class CoilLoader : IImageLoader<WebView> {
    override fun loadInto(url: String, container: WebView) {
        Coil.load(container.context, url)
    }
}