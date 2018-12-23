package com.app.cellstudio.hacker_news_app.presentation.view.activity

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import android.widget.Toast
import com.app.cellstudio.androidkotlincleanboilerplate.di.modules.MainModule
import com.app.cellstudio.hacker_news_app.R
import com.app.cellstudio.hacker_news_app.interactor.viewmodel.MainViewModel
import com.app.cellstudio.hacker_news_app.presentation.BaseApplication
import com.app.cellstudio.hacker_news_app.presentation.adapter.TopStoriesAdapter
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject



class MainActivity : BaseActivity() {

    @Inject
    lateinit var mainViewModel: MainViewModel

    private var topStoriesAdapter: TopStoriesAdapter? = null

    override fun getLayoutResource(): Int {
        return R.layout.activity_main
    }

    override fun getRootView(): View {
        return rlMain
    }

    override fun getToolbarTitle(): String {
        return getString(R.string.app_name)
    }

    override fun onInject() {
        BaseApplication.getInstance()
            .getApplicationComponent()
            .plus(MainModule())
            .inject(this)
    }

    override fun onBindData(view: View?, savedInstanceState: Bundle?) {
        super.onBindData(view, savedInstanceState)
        this.getIsLoading()
        this.getTopStories()

        srlMainContainer.setOnRefreshListener {
            this.getTopStories()
        }

    }

    override fun onResume() {
        super.onResume()
        subscribeSelectedStory()
    }

    private fun getIsLoading() {
        val disposable = mainViewModel.getLoading()
            .compose(bindToLifecycle())
            .observeOn(getUiScheduler())
            .subscribe {
                srlMainContainer.isRefreshing = it
                rvTopStories.visibility = if (it) View.GONE else View.VISIBLE
            }

        compositeDisposable.add(disposable)
    }

    private fun setupStoriesList(topStories: List<Int>) {
        if (topStoriesAdapter != null) {
            topStoriesAdapter!!.updateData(topStories)
            return
        }

        val layoutManager = LinearLayoutManager(this,  LinearLayoutManager.VERTICAL, false)
        rvTopStories.layoutManager = layoutManager
        topStoriesAdapter = TopStoriesAdapter(topStories.toMutableList())
        topStoriesAdapter!!.setListener(object : TopStoriesAdapter.Listener {
            override fun onFirstVisible(id: Int, position: Int) {
                val disposable = mainViewModel.getHackerNewsItemModel(id)
                    .compose(bindToLifecycle())
                    .subscribeOn(getIoScheduler())
                    .observeOn(getUiScheduler())
                    .subscribe {
                        topStoriesAdapter!!.updateViewHolder(position, it)
                    }

                compositeDisposable.add(disposable)
            }
        })
        rvTopStories.adapter = topStoriesAdapter
        rvTopStories.isNestedScrollingEnabled = false
        subscribeSelectedStory()
    }

    private fun subscribeSelectedStory() {
        if (topStoriesAdapter == null)
            return

        val disposable = topStoriesAdapter!!.getSelectedModel()
            .compose(bindToLifecycle())
            .observeOn(getUiScheduler())
            .subscribe { selectedModel ->
                navigator.navigateToDetails(this, selectedModel)
            }

        compositeDisposable.add(disposable)
    }

    private fun getTopStories() {
        val disposable = mainViewModel.getTopStories()
            .compose(bindToLifecycle())
            .subscribeOn(getIoScheduler())
            .observeOn(getUiScheduler())
            .subscribe ({
                setupStoriesList(it)
            }, {throwable: Throwable? -> throwable?.printStackTrace()
                Toast.makeText(this, this.getText(R.string.no_articles_available), Toast.LENGTH_SHORT).show()
            })

        compositeDisposable.add(disposable)
    }

    companion object {

        private val TAG = MainActivity::class.java.simpleName

        fun getCallingIntent(context: Context): Intent {
            val intent = Intent(context, MainActivity::class.java)
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK)
            return intent
        }
    }
}