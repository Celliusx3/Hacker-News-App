package com.app.cellstudio.androidkotlincleanboilerplate.di.components

import com.app.cellstudio.androidkotlincleanboilerplate.di.modules.MainModule
import com.app.cellstudio.hacker_news_app.presentation.view.activity.MainActivity
import dagger.Subcomponent

@Subcomponent(modules = [MainModule::class])
interface MainComponent {
    fun inject(mainActivity: MainActivity)
}
