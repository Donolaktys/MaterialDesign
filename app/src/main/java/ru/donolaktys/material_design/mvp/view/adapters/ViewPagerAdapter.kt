package ru.donolaktys.material_design.mvp.view.adapters

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import ru.donolaktys.material_design.mvp.model.entity.PODServerResponseData
import ru.donolaktys.material_design.ui.fragment.ImageFragment
import ru.donolaktys.material_design.ui.fragment.VideoFragment

class ViewPagerAdapter(
    private val fragmentManager: FragmentManager,
    val data: List<PODServerResponseData>
) :
    FragmentStatePagerAdapter(fragmentManager) {

    override fun getItem(position: Int): Fragment {
        return when (position) {
            0 ->
                if (data[2].mediaType == "image") { ImageFragment(data[2]) } else { VideoFragment(data[2]) }
            1 ->
                if (data[1].mediaType == "image") { ImageFragment(data[1]) } else { VideoFragment(data[1]) }
            2 ->
                if (data[0].mediaType == "image") { ImageFragment(data[0]) } else { VideoFragment(data[0]) }
            else ->
                if (data[2].mediaType == "image") { ImageFragment(data[2]) } else { VideoFragment(data[2]) }
        }
    }

    override fun getCount(): Int {
        return data.size
    }

    override fun getPageTitle(position: Int): CharSequence {
        return when (position) {
            0 -> "Сегодня"
            1 -> "Вчера"
            2 -> "Позавчера"
            else -> "Сегодня"
        }
    }
}