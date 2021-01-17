package ru.donolaktys.material_design.ui.image

import okhttp3.Interceptor
import java.io.IOException

class PODInterceptor : Interceptor {

    @Throws(IOException::class)
    override fun intercept(chain: Interceptor.Chain): okhttp3.Response {
        return chain.proceed(chain.request())
    }
}