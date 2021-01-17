package ru.donolaktys.material_design.mvp.presenter

import moxy.MvpPresenter
import ru.donolaktys.material_design.mvp.view.IPodView
import ru.terrakok.cicerone.Router
import javax.inject.Inject

class PodPresenter : MvpPresenter<IPodView>() {

    @Inject
    lateinit var router: Router

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
    }

    fun backClick() : Boolean{
        router.exit()
        return true
    }
}