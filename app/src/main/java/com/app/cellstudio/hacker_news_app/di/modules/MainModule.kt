package com.app.cellstudio.androidkotlincleanboilerplate.di.modules

import com.app.cellstudio.androidkotlincleanboilerplate.interactor.scheduler.BaseSchedulerProvider
import com.app.cellstudio.hacker_news_app.data.repository.HackerNewsRepository
import com.app.cellstudio.hacker_news_app.interactor.viewmodel.MainViewModel
import com.app.cellstudio.hacker_news_app.interactor.viewmodel.impl.MainViewModelImpl
import dagger.Module
import dagger.Provides

@Module
class MainModule {

    @Provides
    fun provideMainViewModel(hackerNewsRepository: HackerNewsRepository, provider: BaseSchedulerProvider): MainViewModel {
        return MainViewModelImpl(hackerNewsRepository, provider)
    }
}