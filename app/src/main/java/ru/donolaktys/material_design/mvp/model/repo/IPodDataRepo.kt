package ru.donolaktys.material_design.mvp.model.repo

import io.reactivex.rxjava3.core.Single
import ru.donolaktys.material_design.mvp.model.entity.PODServerResponseData

interface IPodDataRepo {
    fun getPictureOfTheDay(apiKey : String) : Single<PODServerResponseData>
}