package ru.donolaktys.material_design.mvp.model.api

import android.annotation.SuppressLint
import java.text.SimpleDateFormat
import java.util.*

class StartDate : IStartDate {

    @SuppressLint("SimpleDateFormat")
    val sdf = SimpleDateFormat("yyyy-MM-dd")
    val currentDate = sdf.format(Date())

    val calendar = Calendar.getInstance()

    @Suppress("NULLABILITY_MISMATCH_BASED_ON_JAVA_ANNOTATIONS")
    override fun getStartDate(): String {
        calendar.time = sdf.parse(currentDate)
        calendar.add(Calendar.DATE, -2)
        val startDate = sdf.format(calendar.time)
        return startDate
    }

}