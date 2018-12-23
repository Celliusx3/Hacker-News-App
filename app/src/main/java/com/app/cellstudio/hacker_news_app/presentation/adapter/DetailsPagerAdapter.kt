package com.app.cellstudio.hacker_news_app.presentation.adapter

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentStatePagerAdapter
import com.app.cellstudio.hacker_news_app.interactor.model.HackerNewsItemModel
import com.app.cellstudio.hacker_news_app.interactor.model.PageModel
import com.app.cellstudio.hacker_news_app.presentation.view.fragment.ArticleFragment
import com.app.cellstudio.hacker_news_app.presentation.view.fragment.CommentsFragment
import java.util.*

class DetailsPagerAdapter(fragmentManager: FragmentManager,
                       private val fragmentPages: List<PageModel>, private val hackerNewsItemModel: HackerNewsItemModel) : FragmentStatePagerAdapter(fragmentManager) {

    private val fragmentPageIds = ArrayList<Int>()

    private var articlesFragment: ArticleFragment? = null
    private var commentsFragment: CommentsFragment? = null

    init {
        fragmentPageIds.clear()
        for (page in fragmentPages) {
            fragmentPageIds.add(page.pageId)
        }
    }

    override fun getItem(position: Int): Fragment? {
        val fragmentPage = fragmentPages[position]

        return when (fragmentPage) {
            PageModel.ArticlePage -> {
                if (articlesFragment == null) {
                    articlesFragment = ArticleFragment.newInstance(hackerNewsItemModel.url)
                }
                articlesFragment
            }
            else -> {
                if (commentsFragment == null) {
                    commentsFragment = CommentsFragment.newInstance(hackerNewsItemModel.children)
                }
                commentsFragment
            }
        }
    }

    override fun getCount(): Int {
        return fragmentPages.size
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return fragmentPages[position].title
    }
}