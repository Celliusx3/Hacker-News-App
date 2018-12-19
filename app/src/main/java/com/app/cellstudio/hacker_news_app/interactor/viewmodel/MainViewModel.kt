package com.app.cellstudio.hacker_news_app.interactor.viewmodel

import android.databinding.ObservableBoolean
import com.app.cellstudio.hacker_news_app.interactor.model.HackerNewsItemModel
import io.reactivex.Observable

interface MainViewModel : ViewModel {
    fun getTopStories(): Observable<List<Int>>
    fun getHackerNewsItemModel(id: Int): Observable<HackerNewsItemModel>
    fun getLoading(): ObservableBoolean
}
