package ru.donolaktys.material_design.mvp.presenter

import moxy.MvpPresenter
import ru.donolaktys.material_design.mvp.view.IMainView
import ru.donolaktys.material_design.navigation.Screens
import ru.terrakok.cicerone.Router

class MainPresenter(val router: Router) : MvpPresenter<IMainView>() {

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        router.replaceScreen(Screens.PodScreen())
    }

    fun backClick() {
        router.exit()
    }
}