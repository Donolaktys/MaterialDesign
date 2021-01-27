package ru.donolaktys.material_design.ui.fragment

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import moxy.MvpAppCompatFragment
import moxy.ktx.moxyPresenter
import ru.donolaktys.material_design.R
import ru.donolaktys.material_design.databinding.FragmentSettingsBinding
import ru.donolaktys.material_design.mvp.presenter.SettingsPresenter
import ru.donolaktys.material_design.mvp.view.ISettingsView
import ru.donolaktys.material_design.ui.App
import ru.donolaktys.material_design.ui.BackButtonListener
import ru.terrakok.cicerone.Router
import javax.inject.Inject
import javax.inject.Provider

class SettingsFragment : MvpAppCompatFragment(), ISettingsView, BackButtonListener {

    @Inject
    lateinit var router: Router

    @Inject
    lateinit var routerProvider: Provider<SettingsPresenter>
    val preferences = activity?.getPreferences(Context.MODE_PRIVATE)

    companion object {
        fun newInstance() = SettingsFragment()
    }

    val presenter: SettingsPresenter by moxyPresenter {
        routerProvider.get()
    }

    init {
        App.component.inject(this)
    }

    private var binding: FragmentSettingsBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSettingsBinding.inflate(inflater, container, false)
        init()
        return binding?.root
    }

    override fun setChangeListener() {
        binding?.rgTheme?.setOnCheckedChangeListener { _, checkedId ->
            preferences?.let {
                when (checkedId) {
                    binding?.radioButtonTheme1?.id -> putPreferenceTheme("Default")
                    binding?.radioButtonTheme1?.id -> putPreferenceTheme("Orange")
                }

            }
        }
    }

    fun putPreferenceTheme(theme: String) {
        preferences?.let {
            when (theme) {
                "Default" ->
                    with(it.edit()) {
                        putString (getString(R.string.theme_preferences_key), getString(R.string.default_theme_name))
                        apply()
                    }
                "Orange" ->
                    with(it.edit()) {
                        putString (getString(R.string.theme_preferences_key), getString(R.string.orange_theme_name))
                        apply()
                    }
            }
            activity?.recreate()
        }
    }

    override fun onDestroy() {
        binding = null
        super.onDestroy()
    }

    private fun init() {
        val defValue = getString(R.string.default_theme_name)
        if (preferences != null) {
            val theme = preferences.getString(getString(R.string.theme_preferences_key), defValue)
            if (theme != defValue) {
                binding?.radioButtonTheme2?.setChecked(true)
            } else {
                return
            }
        } else { binding?.radioButtonTheme1?.setChecked(true) }
    }

    override fun backPressed() = presenter.backClick()
}