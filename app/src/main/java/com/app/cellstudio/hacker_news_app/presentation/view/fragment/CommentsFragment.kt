package com.app.cellstudio.hacker_news_app.presentation.view.fragment

import android.os.Bundle
import com.app.cellstudio.hacker_news_app.R

class CommentsFragment : BaseFragment() {

    override fun getLayoutResource(): Int {
        return R.layout.fragment_comments
    }

    override fun onInject() {}

    companion object {

        private val TAG = CommentsFragment::class.java.simpleName

        fun newInstance(): CommentsFragment {
            val args = Bundle()
            val fragment = CommentsFragment()
            fragment.arguments = args
            return fragment
        }
    }
}
