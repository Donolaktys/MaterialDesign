package ru.donolaktys.material_design.mvp.presenter

import moxy.MvpPresenter
import ru.donolaktys.material_design.mvp.view.IMainView
import ru.donolaktys.material_design.navigation.Screens
import ru.terrakok.cicerone.Router
import javax.inject.Inject

class MainPresenter : MvpPresenter<IMainView>() {

    @Inject
    lateinit var router: Router

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        router.replaceScreen(Screens.PodScreen())
    }

    fun backClick() {
        router.exit()
    }
}