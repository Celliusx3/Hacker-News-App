package com.app.cellstudio.hacker_news_app.interactor.viewmodel.impl

import com.app.cellstudio.hacker_news_app.interactor.model.PageModel
import com.app.cellstudio.hacker_news_app.interactor.viewmodel.BaseViewModelTest
import org.junit.Assert
import org.junit.Test
import java.util.*

class DetailsViewModelImplTest : BaseViewModelTest() {

    lateinit var viewModel: DetailsViewModelImpl

    override fun setUp() {
        super.setUp()
        viewModel = DetailsViewModelImpl(getScheduler())
    }

    /**
     * As User I want to see Article and Fragment in Details View
     */
    @Test
    fun getDetailsPageModel_returnArticleAndFragmentPage() {

        val fragmentPages = ArrayList<PageModel>()

        fragmentPages.add(PageModel.ArticlePage)
        fragmentPages.add(PageModel.CommentsPage)

        viewModel.getDetailsPageModel()
            .subscribeOn(getScheduler().io())
            .observeOn(getScheduler().ui())
            .subscribe({ pages ->
                Assert.assertNotNull(pages)
                Assert.assertEquals(pages, fragmentPages)
            }, { Assert.fail("Failure on testing") })
    }
}