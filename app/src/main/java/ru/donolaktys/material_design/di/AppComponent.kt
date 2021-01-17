package ru.donolaktys.material_design.di

import dagger.Component
import ru.donolaktys.material_design.di.modules.ApiModule
import ru.donolaktys.material_design.di.modules.CiceroneModule
import ru.donolaktys.material_design.di.modules.ImageModule
import ru.donolaktys.material_design.mvp.presenter.MainPresenter
import ru.donolaktys.material_design.ui.activity.MainActivity
import ru.donolaktys.material_design.ui.fragment.PodFragment
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        ApiModule::class,
        CiceroneModule::class,
        ImageModule::class
    ]
)
interface AppComponent {
    fun inject(mainActivity: MainActivity)
    fun inject(mainPresenter: MainPresenter)
    fun inject(podFragment: PodFragment)
}