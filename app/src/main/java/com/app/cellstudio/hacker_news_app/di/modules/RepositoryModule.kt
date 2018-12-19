package com.app.cellstudio.hacker_news_app.di.modules

import com.app.cellstudio.data.http.HttpClient
import com.app.cellstudio.hacker_news_app.data.repository.HackerNewsRepository
import com.app.cellstudio.hacker_news_app.data.repository.impl.HackerNewsRepositoryImpl
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class RepositoryModule {

    @Provides
    @Singleton
    fun provideHackerNewsRepository(httpClient: HttpClient): HackerNewsRepository {
        return HackerNewsRepositoryImpl(httpClient)
    }
}