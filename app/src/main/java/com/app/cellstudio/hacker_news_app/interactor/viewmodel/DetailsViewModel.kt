package com.app.cellstudio.hacker_news_app.interactor.viewmodel

import com.app.cellstudio.hacker_news_app.interactor.model.PageModel
import io.reactivex.Observable

interface DetailsViewModel : ViewModel {
    fun getDetailsPageModel(): Observable<List<PageModel>>
}
