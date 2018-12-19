package com.app.cellstudio.hacker_news_app.data.repository.impl

import com.app.cellstudio.data.http.HttpClient
import com.app.cellstudio.hacker_news_app.data.api.HackerNewsFirebaseService
import com.app.cellstudio.hacker_news_app.data.api.HackerNewsService
import com.app.cellstudio.hacker_news_app.data.repository.HackerNewsRepository
import com.app.cellstudio.hacker_news_app.interactor.model.HackerNewsItemModel
import io.reactivex.Observable

class HackerNewsRepositoryImpl(private val httpClient: HttpClient) : HackerNewsRepository {
    private fun getHackerNewsFirebaseApiService(): HackerNewsFirebaseService {
        return httpClient.getHackerNewsFirebaseApiService()
    }

    private fun getHackerNewsApiService(): HackerNewsService {
        return httpClient.getHackerNewsApiService()
    }

    override fun getTopStories(limitToStart: Int): Observable<List<Int>> {
        return getHackerNewsFirebaseApiService().getTopStories( "\"\$key\"", limitToStart)
    }

    override fun getHackerNewsItem(id: Int): Observable<HackerNewsItemModel> {
        return getHackerNewsApiService().getHackerNewsItem(id)
            .map { HackerNewsItemModel.create(it) }
    }
}