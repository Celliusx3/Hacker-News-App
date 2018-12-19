package com.app.cellstudio.hacker_news_app.presentation.view.activity

import android.content.Context
import android.content.Intent
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import com.app.cellstudio.androidkotlincleanboilerplate.di.modules.MainModule
import com.app.cellstudio.hacker_news_app.R
import com.app.cellstudio.hacker_news_app.databinding.ActivityMainBinding
import com.app.cellstudio.hacker_news_app.interactor.viewmodel.MainViewModel
import com.app.cellstudio.hacker_news_app.presentation.BaseApplication
import com.app.cellstudio.hacker_news_app.presentation.adapter.TopStoriesAdapter
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject

class MainActivity : BaseActivity() {

    @Inject
    lateinit var mainViewModel: MainViewModel

    private lateinit var binding: ActivityMainBinding
    private var topStoriesAdapter: TopStoriesAdapter? = null
    private var topStories: List<Int>? = null

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

        val disposable = mainViewModel.getTopStories()
            .compose(bindToLifecycle())
            .subscribeOn(getIoScheduler())
            .observeOn(getUiScheduler())
            .subscribe {
                this.topStories = it
                setupMoviesList(it)
            }

        compositeDisposable.add(disposable)

        binding = DataBindingUtil.bind(view!!)!!
        binding.viewModel = mainViewModel
    }

    override fun onResume() {
        super.onResume()
        subscribeSelectedMovie()
    }

    private fun setupMoviesList(topStories: List<Int>) {
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
        subscribeSelectedMovie()
    }

    private fun subscribeSelectedMovie() {
        if (topStoriesAdapter == null)
            return

        val disposable = topStoriesAdapter!!.getSelectedModel()
            .compose(bindToLifecycle())
            .observeOn(getUiScheduler())
            .subscribe { selectedMovie -> navigator.navigateToDetails(this, selectedMovie) }
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