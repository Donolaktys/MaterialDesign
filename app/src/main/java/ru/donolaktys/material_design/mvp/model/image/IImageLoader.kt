package ru.donolaktys.material_design.mvp.model.image

interface IImageLoader<T> {
    fun loadInto(url: String, container: T)
}