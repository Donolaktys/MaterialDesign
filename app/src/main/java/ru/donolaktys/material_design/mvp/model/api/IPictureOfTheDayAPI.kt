package ru.donolaktys.material_design.mvp.model.api

import io.reactivex.rxjava3.core.Single
import retrofit2.http.GET
import retrofit2.http.Query
import ru.donolaktys.material_design.mvp.model.entity.PODServerResponseData

interface IPictureOfTheDayAPI {
    @GET("planetary/apod")
    fun getPictureOfTheDay(@Query("api_key") apiKey: String): Single<PODServerResponseData>

    @GET("planetary/apod")
    fun getLastThreeDaysValue(@Query("api_key") apiKey: String, @Query("start_date") startDate: String) : Single<List<PODServerResponseData>>
}