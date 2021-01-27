package ru.donolaktys.material_design.navigation

import ru.donolaktys.material_design.ui.fragment.PodFragment
import ru.donolaktys.material_design.ui.fragment.SettingsFragment
import ru.terrakok.cicerone.android.support.SupportAppScreen

class Screens {
    class PodScreen: SupportAppScreen() {
        override fun getFragment() = PodFragment.newInstance()
    }

    class SettingsScreen: SupportAppScreen() {
        override fun getFragment() = SettingsFragment.newInstance()
    }
}