package ru.donolaktys.material_design.ui

import android.app.Application
import ru.donolaktys.material_design.di.AppComponent
import ru.donolaktys.material_design.di.DaggerAppComponent
import ru.donolaktys.material_design.di.modules.AppModule

class App: Application() {

    companion object {
        lateinit var instance: App
        val component get() = instance._appComponent
    }

    private lateinit var _appComponent: AppComponent

    override fun onCreate() {
        super.onCreate()
        instance = this
        _appComponent = DaggerAppComponent
            .builder()
            .appModule(AppModule(this))
            .build()
    }
}