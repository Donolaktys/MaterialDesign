package ru.donolaktys.material_design.mvp.view

import moxy.MvpView
import moxy.viewstate.strategy.alias.AddToEndSingle
import ru.donolaktys.material_design.mvp.model.entity.PODServerResponseData

interface IPodView : MvpView {
    @AddToEndSingle
    fun init()
    @AddToEndSingle
    fun onError(error : String)
    @AddToEndSingle
    fun onSuccess(podData: PODServerResponseData)
}