package ru.donolaktys.material_design.mvp.presenter

import io.reactivex.rxjava3.core.Scheduler
import moxy.MvpPresenter
import ru.donolaktys.material_design.BuildConfig
import ru.donolaktys.material_design.mvp.model.repo.IPodDataRepo
import ru.donolaktys.material_design.mvp.view.IPodView
import ru.donolaktys.material_design.navigation.Screens
import ru.terrakok.cicerone.Router
import javax.inject.Inject

class PodPresenter @Inject constructor(val router: Router, val podRepo : IPodDataRepo, val uiScheduler: Scheduler) : MvpPresenter<IPodView>() {

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        val apiKey: String = BuildConfig.NASA_API_KEY
        if (apiKey.isBlank()) {
            viewState.onError("You need API key")
        } else {
            podRepo.getLastThreeDaysValue(apiKey)
                .observeOn(uiScheduler)
                .subscribe({
                    viewState.onSuccess(it)
                }, { e ->
                    viewState.onError(e.message.toString())
                })
        }
    }

    fun backClick() : Boolean{
        router.exit()
        return true
    }

    fun toSettings() {
        router.navigateTo(Screens.SettingsScreen())
    }
}