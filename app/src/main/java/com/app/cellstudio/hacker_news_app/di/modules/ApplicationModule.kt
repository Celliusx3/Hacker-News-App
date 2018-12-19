package com.app.cellstudio.androidkotlincleanboilerplate.di.modules

import android.content.Context
import com.app.cellstudio.androidkotlincleanboilerplate.interactor.scheduler.BaseSchedulerProvider
import com.app.cellstudio.androidkotlincleanboilerplate.interactor.scheduler.SchedulerProvider
import com.app.cellstudio.data.environment.BaseEnvironment
import com.app.cellstudio.data.environment.Environment
import com.app.cellstudio.data.http.BaseHttpClient
import com.app.cellstudio.data.http.HttpClient
import com.app.cellstudio.hacker_news_app.presentation.BaseApplication
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class ApplicationModule(private val mApplication: BaseApplication) {

    @Provides
    @Singleton
    fun provideApplication(): BaseApplication {
        return mApplication
    }

    @Provides
    @Singleton
    fun provideApplicationContext(baseApplication: BaseApplication): Context {
        return baseApplication
    }

    @Provides
    @Singleton
    fun provideScheduler(): BaseSchedulerProvider {
        return SchedulerProvider.getInstance()
    }

    @Provides
    @Singleton
    fun provideHttpClient(environment: Environment): HttpClient {
        return BaseHttpClient(environment)
    }

    @Provides
    @Singleton
    fun provideEnvironment(): Environment {
        return BaseEnvironment()
    }
}