package com.app.cellstudio.hacker_news_app.di.modules

import com.app.cellstudio.androidkotlincleanboilerplate.interactor.scheduler.BaseSchedulerProvider
import com.app.cellstudio.hacker_news_app.interactor.viewmodel.DetailsViewModel
import com.app.cellstudio.hacker_news_app.interactor.viewmodel.impl.DetailsViewModelImpl
import dagger.Module
import dagger.Provides

@Module
class DetailsModule {

    @Provides
    fun provideDetailsViewModel(provider: BaseSchedulerProvider): DetailsViewModel {
        return DetailsViewModelImpl (provider)
    }
}