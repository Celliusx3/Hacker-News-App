package com.app.cellstudio.androidkotlincleanboilerplate.di.components

import android.content.Context
import com.app.cellstudio.androidkotlincleanboilerplate.di.modules.ApplicationModule
import com.app.cellstudio.androidkotlincleanboilerplate.di.modules.MainModule
import com.app.cellstudio.hacker_news_app.di.modules.RepositoryModule
import com.app.cellstudio.hacker_news_app.presentation.BaseApplication
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [ApplicationModule::class, RepositoryModule::class])
interface ApplicationComponent {
    fun getApplication(): BaseApplication
    fun getApplicationContext(): Context
    fun inject(baseApplication: BaseApplication)
    fun plus(mainModule: MainModule): MainComponent
}