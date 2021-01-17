package ru.donolaktys.material_design.di.modules

import dagger.Module
import dagger.Provides
import ru.donolaktys.material_design.ui.App

@Module
class AppModule(val app: App) {

    @Provides
    fun app() : App {
        return app
    }
}