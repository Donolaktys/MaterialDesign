package ru.donolaktys.material_design.ui.fragment

import moxy.MvpAppCompatFragment
import ru.donolaktys.material_design.mvp.view.IPodView
import ru.donolaktys.material_design.ui.BackButtonListener

class PodFragment : MvpAppCompatFragment(), IPodView, BackButtonListener {


    companion object {
        fun newInstance() = PodFragment()
    }

    override fun backPressed(): Boolean {
        TODO("Not yet implemented")
    }

}