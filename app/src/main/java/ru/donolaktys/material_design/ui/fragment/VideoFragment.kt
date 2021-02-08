package ru.donolaktys.material_design.ui.fragment

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import ru.donolaktys.material_design.databinding.FragmentVideoBinding
import ru.donolaktys.material_design.mvp.model.entity.PODServerResponseData

class VideoFragment(val data: PODServerResponseData) : Fragment() {

    var binding: FragmentVideoBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentVideoBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init()
    }

    @SuppressLint("SetJavaScriptEnabled")
    fun init() {
        binding?.let { bind ->
            data.url?.let {
                val view = bind.webView
                view.clearCache(true)
                view.clearHistory()
                view.settings.javaScriptEnabled = true
                view.settings.javaScriptCanOpenWindowsAutomatically = true
                view.loadUrl(it)
            }
            data.title?.let {
                bind.webViewDescriptionHeader.text = it
            }
            data.explanation?.let {
                bind.webViewDescription.text = it
            }
        }
    }

    override fun onDestroy() {
        binding = null
        super.onDestroy()
    }
}