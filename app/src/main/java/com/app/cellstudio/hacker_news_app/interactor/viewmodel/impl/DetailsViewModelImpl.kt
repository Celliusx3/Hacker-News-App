package com.app.cellstudio.hacker_news_app.interactor.viewmodel.impl

import com.app.cellstudio.androidkotlincleanboilerplate.interactor.scheduler.BaseSchedulerProvider
import com.app.cellstudio.hacker_news_app.interactor.model.PageModel
import com.app.cellstudio.hacker_news_app.interactor.viewmodel.BaseViewModel
import com.app.cellstudio.hacker_news_app.interactor.viewmodel.DetailsViewModel
import io.reactivex.Observable
import java.util.*

class DetailsViewModelImpl (
    scheduler: BaseSchedulerProvider
) : BaseViewModel(scheduler), DetailsViewModel {
    override fun getDetailsPageModel(): Observable<List<PageModel>> {
        val fragmentPages = ArrayList<PageModel>()

        fragmentPages.add(PageModel.ArticlePage)
        fragmentPages.add(PageModel.CommentsPage)

        return Observable.just<List<PageModel>>(fragmentPages)    }
}