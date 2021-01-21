package ru.donolaktys.material_design.mvp.model.repo.retrofit

import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers
import ru.donolaktys.material_design.mvp.model.api.IPictureOfTheDayAPI
import ru.donolaktys.material_design.mvp.model.repo.IPodDataRepo

class RetrofitPodDataRepo(val api: IPictureOfTheDayAPI) : IPodDataRepo {

    override fun getPictureOfTheDay(apiKey : String) = api.getPictureOfTheDay(apiKey).flatMap {
        Single.just(it)
    }.observeOn(Schedulers.io())

}