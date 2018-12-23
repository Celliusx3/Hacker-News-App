package com.app.cellstudio.hacker_news_app.interactor.viewmodel

import com.app.cellstudio.androidkotlincleanboilerplate.interactor.scheduler.BaseSchedulerProvider
import com.app.cellstudio.hacker_news_app.data.entities.HackerNewsItem
import com.app.cellstudio.hacker_news_app.data.repository.HackerNewsRepository
import com.app.cellstudio.hacker_news_app.interactor.model.HackerNewsItemModel
import com.app.cellstudio.hacker_news_app.interactor.scheduler.ImmediateSchedulerProvider
import org.junit.Before
import org.mockito.Mock
import org.mockito.MockitoAnnotations

open class BaseViewModelTest {
    @Mock
    lateinit var hackerNewsRepository: HackerNewsRepository

    protected fun getScheduler(): BaseSchedulerProvider {
        return ImmediateSchedulerProvider()
    }

    @Before
    open fun setUp() {
        MockitoAnnotations.initMocks(this)
    }

    protected fun createDefaultHackerNewsItemModel(): HackerNewsItemModel {
        return  createHackerNewsItemModel(HackerNewsItem())
    }

    private fun createHackerNewsItemModel(hackerNewsItem: HackerNewsItem): HackerNewsItemModel {
        return HackerNewsItemModel.create(hackerNewsItem)
    }

}