package ru.donolaktys.material_design.di

import dagger.Component
import ru.donolaktys.material_design.di.modules.*
import ru.donolaktys.material_design.ui.activity.MainActivity
import ru.donolaktys.material_design.ui.fragment.PodFragment
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        AppModule::class,
        ApiModule::class,
        CiceroneModule::class
    ]
)
interface AppComponent {
    fun inject(mainActivity: MainActivity)
    fun inject(podFragment: PodFragment)
}