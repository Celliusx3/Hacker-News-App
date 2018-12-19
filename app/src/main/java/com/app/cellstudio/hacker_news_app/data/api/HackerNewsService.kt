package com.app.cellstudio.hacker_news_app.data.api

import com.app.cellstudio.hacker_news_app.data.entities.HackerNewsItem
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Path

interface HackerNewsService {
    @GET(ApiRoutes.HACKER_NEWS_ITEM + "/{id}")
    fun getHackerNewsItem(@Path("id") id : Int): Observable<HackerNewsItem>
}