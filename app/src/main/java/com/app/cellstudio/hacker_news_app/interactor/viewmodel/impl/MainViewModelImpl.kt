package com.app.cellstudio.hacker_news_app.interactor.viewmodel.impl

import com.app.cellstudio.androidkotlincleanboilerplate.interactor.scheduler.BaseSchedulerProvider
import com.app.cellstudio.hacker_news_app.data.repository.HackerNewsRepository
import com.app.cellstudio.hacker_news_app.interactor.model.HackerNewsItemModel
import com.app.cellstudio.hacker_news_app.interactor.viewmodel.BaseViewModel
import com.app.cellstudio.hacker_news_app.interactor.viewmodel.MainViewModel
import io.reactivex.Observable
import io.reactivex.subjects.PublishSubject

class MainViewModelImpl(
    private val hackerNewsRepository: HackerNewsRepository,
    scheduler: BaseSchedulerProvider
) : BaseViewModel(scheduler), MainViewModel {

    private val isLoading = PublishSubject.create<Boolean>()

    override fun getLoading(): Observable<Boolean> {
       return isLoading
    }

    override fun getTopStories(): Observable<List<Int>> {
        isLoading.onNext(true)
        return hackerNewsRepository.getTopStories(LIMIT_TO_START)
            .doFinally { isLoading.onNext(false) }
    }

    override fun getHackerNewsItemModel(id: Int): Observable<HackerNewsItemModel> {
        return hackerNewsRepository.getHackerNewsItem(id)
    }

    companion object {
        private const val LIMIT_TO_START = 25
    }
}
