package ru.donolaktys.material_design.mvp.model.entity

import android.os.Parcelable
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class PODServerResponseData(
    @Expose @SerializedName("copyright") val copyright:String?,
    @Expose @SerializedName("date") val date:String?,
    @Expose @SerializedName("explanation")val explanation:String?,
    @Expose @SerializedName("media_type")val mediaType:String?,
    @Expose @SerializedName("title")val title:String?,
    @Expose @SerializedName("url")val url:String?,
    @Expose @SerializedName("hdurl")val hdurl:String?
) : Parcelable