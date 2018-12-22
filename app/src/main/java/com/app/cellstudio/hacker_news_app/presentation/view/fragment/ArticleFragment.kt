package com.app.cellstudio.hacker_news_app.presentation.view.fragment

import android.graphics.Bitmap
import android.os.Bundle
import android.view.View
import android.webkit.WebChromeClient
import android.webkit.WebView
import android.webkit.WebViewClient
import com.app.cellstudio.hacker_news_app.R
import com.app.cellstudio.hacker_news_app.presentation.BaseApplication
import kotlinx.android.synthetic.main.fragment_articles.*



class ArticleFragment : BaseFragment() {

    private lateinit var url: String

    override fun getLayoutResource(): Int {
        return R.layout.fragment_articles
    }

    override fun onInject() {
        BaseApplication.getInstance()
            .getApplicationComponent()
            .inject(this)
    }

    override fun onBindData(view: View?) {
        super.onBindData(view)

        createWebView()
        wvArticles.loadUrl(url)
    }

    override fun onGetInputData() {
        super.onGetInputData()
        val arguments = this.arguments
        if (arguments != null) {
            url = arguments.getString(EXTRA_WEBVIEW_URL, "")
        }
    }

    private fun createWebView() {
        wvArticles.webChromeClient = object : WebChromeClient() {
            override fun onProgressChanged(view: WebView?, newProgress: Int) {
                super.onProgressChanged(view, newProgress)
                    pbArticles?.progress = newProgress
            }
        }

        wvArticles.webViewClient = object : WebViewClient() {

            override fun onPageStarted(view: WebView?, url: String?, favicon: Bitmap?) {
                super.onPageStarted(view, url, favicon)
                pbArticles?.visibility = View.VISIBLE
            }

            override fun onPageFinished(view: WebView?, url: String?) {
                super.onPageFinished(view, url)
                pbArticles?.visibility = View.GONE
            }
        }
    }

    companion object {

        private val TAG = ArticleFragment::class.java.simpleName
        private val EXTRA_WEBVIEW_URL = "EXTRA_WEBVIEW_URL"

        fun newInstance(url: String): ArticleFragment {
            val args = Bundle().apply {
                putString(EXTRA_WEBVIEW_URL, url)
            }
            val fragment = ArticleFragment()
            fragment.arguments = args
            return fragment
        }
    }
}
