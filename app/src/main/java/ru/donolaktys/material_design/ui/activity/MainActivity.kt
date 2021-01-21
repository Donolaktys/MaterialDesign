package ru.donolaktys.material_design.ui.activity

import android.os.Bundle
import moxy.MvpAppCompatActivity
import moxy.ktx.moxyPresenter
import ru.donolaktys.material_design.databinding.ActivityMainBinding
import ru.donolaktys.material_design.mvp.presenter.MainPresenter
import ru.donolaktys.material_design.mvp.view.IMainView
import ru.donolaktys.material_design.ui.App
import ru.donolaktys.material_design.ui.BackButtonListener
import ru.terrakok.cicerone.NavigatorHolder
import ru.terrakok.cicerone.Router
import ru.terrakok.cicerone.android.support.SupportAppNavigator
import javax.inject.Inject

class MainActivity : MvpAppCompatActivity(), IMainView {

    @Inject lateinit var navigatorHolder : NavigatorHolder
    @Inject lateinit var router: Router

    init {
        App.component.inject(this)
    }
    private lateinit var binding : ActivityMainBinding

    val navigator by lazy { SupportAppNavigator(this, supportFragmentManager, binding.container.id) }

    private val presenter : MainPresenter by moxyPresenter {
        MainPresenter(router)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    override fun onResumeFragments() {
        super.onResumeFragments()
        navigatorHolder.setNavigator(navigator)
    }

    override fun onPause() {
        super.onPause()
        navigatorHolder.removeNavigator()
    }

    override fun onBackPressed() {
        supportFragmentManager.fragments.forEach {
            if(it is BackButtonListener && it.backPressed()){
                return
            }
        }
        presenter.backClick()
    }
}