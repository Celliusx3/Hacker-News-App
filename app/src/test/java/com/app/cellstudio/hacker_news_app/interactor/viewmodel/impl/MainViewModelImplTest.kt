package com.app.cellstudio.hacker_news_app.interactor.viewmodel.impl

import com.app.cellstudio.hacker_news_app.interactor.viewmodel.BaseViewModelTest
import com.app.cellstudio.hacker_news_app.interactor.viewmodel.impl.MainViewModelImpl.Companion.LIMIT_TO_START
import io.reactivex.Observable
import io.reactivex.observers.TestObserver
import org.junit.Assert
import org.junit.Test
import org.mockito.Mockito.`when`

class MainViewModelImplTest : BaseViewModelTest() {

    lateinit var viewModel: MainViewModelImpl

    override fun setUp() {
        super.setUp()
        viewModel = MainViewModelImpl(hackerNewsRepository, getScheduler())
    }

    /**
     * As User I want to see loading animation is shown while getting Top 25 stories
     */
    @Test
    fun getTopStories_LoadingShown() {
        `when`(hackerNewsRepository.getTopStories(LIMIT_TO_START)).thenReturn(Observable.just(ArrayList()))

        val testTopStoriesLoadingObserver = TestObserver<Boolean>()

        viewModel.getLoading().subscribe(testTopStoriesLoadingObserver)
        viewModel.getTopStories()
            .test()
            .assertNoErrors()
            .assertOf { _ -> testTopStoriesLoadingObserver.assertValues(true, false) }
    }

    /**
     * As User I want to get hacker news data using id
     */
    @Test
    fun getHackerNewsItemModel_withId() {
        `when`(hackerNewsRepository.getHackerNewsItem(1)).thenReturn(Observable.just(createDefaultHackerNewsItemModel()))
        viewModel.getHackerNewsItemModel(1)
            .subscribeOn(getScheduler().io())
            .observeOn(getScheduler().ui())
            .subscribe({ hackerNewsItemModel ->
                Assert.assertNotNull(hackerNewsItemModel)
            }, { Assert.fail("Failure on testing") })
    }
}