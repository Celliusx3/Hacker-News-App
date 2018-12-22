package com.app.cellstudio.hacker_news_app.presentation.view.fragment

import android.os.Bundle
import android.os.Parcelable
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import com.app.cellstudio.hacker_news_app.R
import com.app.cellstudio.hacker_news_app.interactor.model.HackerNewsItemModel
import com.app.cellstudio.hacker_news_app.presentation.BaseApplication
import com.app.cellstudio.hacker_news_app.presentation.adapter.CommentsAdapter
import kotlinx.android.synthetic.main.fragment_comments.*



class CommentsFragment : BaseFragment() {

    private var commentsAdapter: CommentsAdapter? = null
    private lateinit var comments: List<HackerNewsItemModel>

    override fun getLayoutResource(): Int {
        return R.layout.fragment_comments
    }

    override fun onInject() {
        BaseApplication.getInstance()
            .getApplicationComponent()
            .inject(this)
    }

    override fun onGetInputData() {
        super.onGetInputData()
        val arguments = this.arguments
        if (arguments != null) {
            comments = arguments.getParcelableArrayList<HackerNewsItemModel>(EXTRA_COMMENTS) as List<HackerNewsItemModel>
        }
    }

    override fun onBindData(view: View?) {
        super.onBindData(view)
        if (comments.isEmpty()) {
            tvNoComments.visibility = View.VISIBLE
        } else {
            tvNoComments.visibility = View.GONE
            setupCommentsList(comments)
        }
    }

    private fun setupCommentsList(comments: List<HackerNewsItemModel>) {
        val layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        rvComments.layoutManager = layoutManager
        commentsAdapter = CommentsAdapter(comments.toMutableList())
        rvComments.adapter = commentsAdapter
        rvComments.isNestedScrollingEnabled = false
    }

    companion object {

        private val TAG = CommentsFragment::class.java.simpleName
        private const val EXTRA_COMMENTS = "EXTRA_COMMENTS"

        fun newInstance(children: List<HackerNewsItemModel>): CommentsFragment {
            val args = Bundle().apply {
                putParcelableArrayList(EXTRA_COMMENTS, children as ArrayList<out Parcelable>)
            }
            val fragment = CommentsFragment()
            fragment.arguments = args
            return fragment
        }
    }
}
