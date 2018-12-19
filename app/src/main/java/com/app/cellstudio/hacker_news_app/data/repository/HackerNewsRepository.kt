package com.app.cellstudio.hacker_news_app.data.repository

import com.app.cellstudio.hacker_news_app.interactor.model.HackerNewsItemModel
import io.reactivex.Observable

interface HackerNewsRepository {
    fun getTopStories(limitToStart: Int): Observable<List<Int>>
    fun getHackerNewsItem(id: Int): Observable<HackerNewsItemModel>
}

