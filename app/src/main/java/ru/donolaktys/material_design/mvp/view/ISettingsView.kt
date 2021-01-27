package ru.donolaktys.material_design.mvp.view

import moxy.MvpView
import moxy.viewstate.strategy.alias.AddToEndSingle

interface ISettingsView: MvpView {

    @AddToEndSingle
    fun setChangeListener()
}