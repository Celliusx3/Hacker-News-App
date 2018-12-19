package com.app.cellstudio.hacker_news_app.interactor.viewmodel.impl

import android.databinding.ObservableBoolean
import com.app.cellstudio.androidkotlincleanboilerplate.interactor.scheduler.BaseSchedulerProvider
import com.app.cellstudio.hacker_news_app.data.repository.HackerNewsRepository
import com.app.cellstudio.hacker_news_app.interactor.model.HackerNewsItemModel
import com.app.cellstudio.hacker_news_app.interactor.viewmodel.BaseViewModel
import com.app.cellstudio.hacker_news_app.interactor.viewmodel.MainViewModel
import io.reactivex.Observable

class MainViewModelImpl(
    private val hackerNewsRepository: HackerNewsRepository,
    scheduler: BaseSchedulerProvider
) : BaseViewModel(scheduler), MainViewModel {

    private val isLoading = ObservableBoolean(false)

    override fun getLoading(): ObservableBoolean {
       return isLoading
    }



    override fun getTopStories(): Observable<List<Int>> {
        isLoading.set(true)
        return hackerNewsRepository.getTopStories(LIMIT_TO_START)
            .doFinally { isLoading.set(false) }
    }

    override fun getHackerNewsItemModel(id: Int): Observable<HackerNewsItemModel> {
        return hackerNewsRepository.getHackerNewsItem(id)
    }

    companion object {
        private val LIMIT_TO_START = 25
    }
}
