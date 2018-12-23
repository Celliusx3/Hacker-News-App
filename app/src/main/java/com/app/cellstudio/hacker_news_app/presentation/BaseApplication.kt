package com.app.cellstudio.hacker_news_app.presentation

import android.app.Application
import com.app.cellstudio.androidkotlincleanboilerplate.di.components.ApplicationComponent
import com.app.cellstudio.androidkotlincleanboilerplate.di.components.DaggerApplicationComponent
import com.app.cellstudio.androidkotlincleanboilerplate.di.modules.ApplicationModule

class BaseApplication : Application() {

    private lateinit var applicationComponent: ApplicationComponent
    private val mLock = Any()

    override fun onCreate() {
        super.onCreate()
        synchronized(mLock) {
            singleton = this
            applicationComponent = DaggerApplicationComponent.builder()
                .applicationModule(ApplicationModule(this))
                .build()
            applicationComponent.inject(this)
        }
    }

    fun getApplicationComponent() : ApplicationComponent {
        return applicationComponent
    }

    companion object {
        // Singleton Instance
        private lateinit var singleton: BaseApplication

        fun getInstance() : BaseApplication { return singleton }
    }

}