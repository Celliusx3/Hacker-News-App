package com.app.cellstudio.androidkotlincleanboilerplate.di.components

import android.content.Context
import com.app.cellstudio.androidkotlincleanboilerplate.di.modules.ApplicationModule
import com.app.cellstudio.androidkotlincleanboilerplate.di.modules.MainModule
import com.app.cellstudio.hacker_news_app.di.components.DetailsComponent
import com.app.cellstudio.hacker_news_app.di.modules.DetailsModule
import com.app.cellstudio.hacker_news_app.di.modules.RepositoryModule
import com.app.cellstudio.hacker_news_app.presentation.BaseApplication
import com.app.cellstudio.hacker_news_app.presentation.view.fragment.ArticleFragment
import com.app.cellstudio.hacker_news_app.presentation.view.fragment.CommentsFragment
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [ApplicationModule::class, RepositoryModule::class])
interface ApplicationComponent {
    fun getApplication(): BaseApplication
    fun getApplicationContext(): Context
    fun inject(baseApplication: BaseApplication)
    fun inject(commentsFragment: CommentsFragment)
    fun inject(articleFragment: ArticleFragment)
    fun plus(mainModule: MainModule): MainComponent
    fun plus(detailsModule: DetailsModule): DetailsComponent
}
