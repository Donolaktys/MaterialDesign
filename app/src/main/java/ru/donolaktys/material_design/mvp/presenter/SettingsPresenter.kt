package ru.donolaktys.material_design.mvp.presenter

import moxy.MvpPresenter
import ru.donolaktys.material_design.mvp.view.ISettingsView
import ru.donolaktys.material_design.navigation.Screens
import ru.terrakok.cicerone.Router
import javax.inject.Inject

class SettingsPresenter @Inject constructor(val router: Router) : MvpPresenter<ISettingsView>() {

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        viewState.setChangeListener()
    }

    fun backClick() : Boolean{
        router.exit()
        return true
    }
}