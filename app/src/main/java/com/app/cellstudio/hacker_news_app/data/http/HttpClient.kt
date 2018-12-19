package com.app.cellstudio.data.http

import com.app.cellstudio.hacker_news_app.data.api.HackerNewsFirebaseService
import com.app.cellstudio.hacker_news_app.data.api.HackerNewsService

interface HttpClient {
    fun getHackerNewsFirebaseApiService() : HackerNewsFirebaseService
    fun getHackerNewsApiService() : HackerNewsService

}