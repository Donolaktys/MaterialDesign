package ru.donolaktys.material_design.ui.fragment

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContextCompat
import com.google.android.material.bottomsheet.BottomSheetBehavior
import io.reactivex.rxjava3.core.Scheduler
import moxy.MvpAppCompatFragment
import moxy.ktx.moxyPresenter
import ru.donolaktys.material_design.R
import ru.donolaktys.material_design.databinding.FragmentPodBinding
import ru.donolaktys.material_design.mvp.model.entity.PODServerResponseData
import ru.donolaktys.material_design.mvp.model.repo.IPodDataRepo
import ru.donolaktys.material_design.mvp.presenter.PodPresenter
import ru.donolaktys.material_design.mvp.view.IPodView
import ru.donolaktys.material_design.mvp.view.adapters.ViewPagerAdapter
import ru.donolaktys.material_design.ui.App
import ru.donolaktys.material_design.ui.BackButtonListener
import ru.donolaktys.material_design.ui.activity.MainActivity
import ru.terrakok.cicerone.Router
import javax.inject.Inject
import javax.inject.Provider

class PodFragment : MvpAppCompatFragment(), IPodView, BackButtonListener {

    @Inject
    lateinit var router: Router

    @Inject
    lateinit var podRepo: IPodDataRepo

    @Inject
    lateinit var uiScheduler: Scheduler

    @Inject
    lateinit var routerProvider: Provider<PodPresenter>

    init {
        App.component.inject(this)
    }

    private var binding: FragmentPodBinding? = null
    private lateinit var vpAdapter : ViewPagerAdapter
    private lateinit var bottomSheetBehavior: BottomSheetBehavior<ConstraintLayout>

    companion object {
        fun newInstance() = PodFragment()
        private var isMain = true
    }

    val presenter: PodPresenter by moxyPresenter {
        routerProvider.get()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentPodBinding.inflate(inflater, container, false)
        val view = binding?.root
        init()
        return view
    }

    override fun onDestroy() {
        binding = null
        super.onDestroy()
    }

    override fun init() {
        binding?.let {
            setBottomAppBar(it.root)
            it.inputLayout.setEndIconOnClickListener {
                startActivity(Intent(Intent.ACTION_VIEW).apply {
                    data =
                        Uri.parse("https://en.wikipedia.org/wiki/${binding?.inputEditText?.text.toString()}")
                })
            }
        }
    }

    override fun onError(error: String) {
        Toast.makeText(requireContext(), error, Toast.LENGTH_SHORT).show()
    }

    override fun onSuccess(podData: List<PODServerResponseData>) {
        vpAdapter = ViewPagerAdapter(childFragmentManager, podData)
        binding?.let {
            it.viewPager.adapter = vpAdapter
            it.vpTabLayout.setupWithViewPager(it.viewPager)
        }
    }

    private fun setBottomAppBar(view: View) {
        val context = activity as MainActivity
        binding?.let {
            context.setSupportActionBar(it.bottomAppBar)
            setHasOptionsMenu(true)
            it.bottomAppBar.navigationIcon =
                ContextCompat.getDrawable(context, R.drawable.ic_hamburger_menu_bottom_bar)
        }
    }
//        .bottomAppBar.navigationIcon =
//            ContextCompat.getDrawable(context, R.drawable.ic_hamburger_menu_bottom_bar)
//
//        binding?.fab?.setOnClickListener {
//            if (isMain) {
//                isMain = false
//                binding?.let {
//                    it.bottomAppBar.navigationIcon = null
//                    it.bottomAppBar.fabAlignmentMode = BottomAppBar.FAB_ALIGNMENT_MODE_END
//                    it.fab.setImageDrawable(
//                        ContextCompat.getDrawable(
//                            context,
//                            R.drawable.ic_back_fab
//                        )
//                    )
//                    it.bottomAppBar.replaceMenu(R.menu.menu_bottom_bar_other_screen)
//                }
//
//            } else {
//                isMain = true
//                binding?.let {
//
//                    it.bottomAppBar.fabAlignmentMode = BottomAppBar.FAB_ALIGNMENT_MODE_CENTER
//                    it.fab.setImageDrawable(
//                        ContextCompat.getDrawable(
//                            context, R.drawable.ic_plus_fab
//                        )
//                    )
//                    it.bottomAppBar.replaceMenu(R.menu.menu_bottom_bar)
//                }
//
//            }
//        }
//    }

    private fun setBottomSheetBehavior(bottomSheet: ConstraintLayout) {
        bottomSheetBehavior = BottomSheetBehavior.from(bottomSheet)
        bottomSheetBehavior.setPeekHeight(350, false)
        bottomSheetBehavior.state = BottomSheetBehavior.STATE_DRAGGING
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.menu_bottom_bar, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.app_bar_search -> toast("Search")
            R.id.app_bar_fav -> toast("Favourite")
            R.id.app_bar_settings -> presenter.toSettings()
            android.R.id.home -> {
                activity?.let {
                    BottomNavigationDrawerFragment().show(it.supportFragmentManager, "tag")
                }
            }
        }
        return super.onOptionsItemSelected(item)
    }

    private fun toast(message: String) =
        Toast.makeText(requireContext(), message, Toast.LENGTH_SHORT).show()

    override fun backPressed() = presenter.backClick()

}