package ru.donolaktys.material_design.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import coil.api.load
import ru.donolaktys.material_design.R
import ru.donolaktys.material_design.databinding.FragmentImageBinding
import ru.donolaktys.material_design.mvp.model.entity.PODServerResponseData

class ImageFragment(val data: PODServerResponseData) : Fragment() {

    var binding: FragmentImageBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentImageBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init()
    }

    fun init() {
        binding?.let { bind ->
            data.url?.let {
                bind.imageView.load(it) {
                    error(R.drawable.ic_load_error_vector)
                    placeholder(R.drawable.ic_no_photo_vector)
                }
            }
            data.title?.let {
                bind.imageViewDescriptionHeader.text = it
            }
            data.explanation?.let {
                bind.imageViewDescription.text = it
            }
        }
    }

    override fun onDestroy() {
        binding = null
        super.onDestroy()
    }
}