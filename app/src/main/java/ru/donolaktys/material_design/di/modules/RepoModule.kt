package ru.donolaktys.material_design.di.modules

import dagger.Module
import dagger.Provides
import ru.donolaktys.material_design.mvp.model.api.IPictureOfTheDayAPI
import ru.donolaktys.material_design.mvp.model.api.IStartDate
import ru.donolaktys.material_design.mvp.model.api.StartDate
import ru.donolaktys.material_design.mvp.model.repo.IPodDataRepo
import ru.donolaktys.material_design.mvp.model.repo.retrofit.RetrofitPodDataRepo
import javax.inject.Singleton

@Module
class RepoModule {
    @Singleton
    @Provides
    fun getRepo(api: IPictureOfTheDayAPI, startDate: IStartDate) : IPodDataRepo = RetrofitPodDataRepo(api, startDate)
}