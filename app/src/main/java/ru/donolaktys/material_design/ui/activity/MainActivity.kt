package ru.donolaktys.material_design.ui.activity

import android.content.SharedPreferences
import android.os.Bundle
import moxy.MvpAppCompatActivity
import moxy.ktx.moxyPresenter
import ru.donolaktys.material_design.R
import ru.donolaktys.material_design.databinding.ActivityMainBinding
import ru.donolaktys.material_design.mvp.presenter.MainPresenter
import ru.donolaktys.material_design.mvp.view.IMainView
import ru.donolaktys.material_design.ui.App
import ru.donolaktys.material_design.ui.BackButtonListener
import ru.terrakok.cicerone.NavigatorHolder
import ru.terrakok.cicerone.Router
import ru.terrakok.cicerone.android.support.SupportAppNavigator
import javax.inject.Inject
import javax.inject.Provider

class MainActivity : MvpAppCompatActivity(), IMainView {

    @Inject
    lateinit var navigatorHolder: NavigatorHolder

    @Inject
    lateinit var router: Router

    @Inject
    lateinit var routerProvider: Provider<MainPresenter>
    private lateinit var binding: ActivityMainBinding
    lateinit var preferences: SharedPreferences

    val navigator by lazy {
        SupportAppNavigator(
            this,
            supportFragmentManager,
            binding.container.id
        )
    }

    private val presenter: MainPresenter by moxyPresenter {
        routerProvider.get()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        App.component.inject(this)
        if (getPrefTheme() == getString(R.string.default_theme_name)) {
            setTheme(R.style.AppTheme)
        } else {
            setTheme(R.style.OrangeTheme)
        }

        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    fun getPrefTheme(): String {
        val defValue = getString(R.string.default_theme_name)
        preferences = getPreferences(MODE_PRIVATE)
        val theme: String = preferences.getString(getString(R.string.theme_preferences_key), defValue) ?: defValue
        return theme
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
            if (it is BackButtonListener && it.backPressed()) {
                return
            }
        }
        presenter.backClick()
    }
}