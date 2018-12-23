package com.app.cellstudio.hacker_news_app.presentation.view.activity

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.design.widget.TabLayout
import android.view.View
import com.app.cellstudio.hacker_news_app.R
import com.app.cellstudio.hacker_news_app.di.modules.DetailsModule
import com.app.cellstudio.hacker_news_app.interactor.model.HackerNewsItemModel
import com.app.cellstudio.hacker_news_app.interactor.model.PageModel
import com.app.cellstudio.hacker_news_app.interactor.viewmodel.DetailsViewModel
import com.app.cellstudio.hacker_news_app.presentation.BaseApplication
import com.app.cellstudio.hacker_news_app.presentation.adapter.DetailsPagerAdapter
import kotlinx.android.synthetic.main.activity_details.*
import kotlinx.android.synthetic.main.toolbar.*
import javax.inject.Inject

class DetailsActivity : BaseActivity() {
    @Inject
    lateinit var detailsViewModel: DetailsViewModel

    private lateinit var detailsPagerAdapter: DetailsPagerAdapter
    private lateinit var hackerNewsItemModel: HackerNewsItemModel

    override fun getLayoutResource(): Int {
        return R.layout.activity_details
    }

    override fun getRootView(): View {
        return rlDetails
    }

    override fun getToolbarTitle(): String {
        return this.getString(R.string.title_details)
    }

    override fun onInject() {
        BaseApplication.getInstance()
            .getApplicationComponent()
            .plus(DetailsModule())
            .inject(this)
    }

    override fun onBindView() {
        super.onBindView()
        toolbar.setNavigationIcon(R.drawable.ic_arrow_back_white_24dp)
        toolbar.setNavigationOnClickListener { onBackPressed() }
    }

    override fun onBindData(view: View?, savedInstanceState: Bundle?) {
        super.onBindData(view, savedInstanceState)

        val disposable = detailsViewModel.getDetailsPageModel()
            .compose(bindToLifecycle())
            .observeOn(getUiScheduler())
            .subscribe {
                setupDetailsPagerAdapter(it)
                setupDetailsTab()
            }

        compositeDisposable.add(disposable)
    }

    override fun onGetInputData(savedInstanceState: Bundle?) {
        super.onGetInputData(savedInstanceState)
        if (intent != null) {
            hackerNewsItemModel = intent.getParcelableExtra(EXTRA_HACKER_NEWS_ITEM)
        }
    }

    private fun setupDetailsPagerAdapter(pages: List<PageModel>) {
        detailsPagerAdapter = DetailsPagerAdapter(supportFragmentManager, pages, hackerNewsItemModel)
        vpDetails.adapter = detailsPagerAdapter
    }

    private fun setupDetailsTab() {
        tlDetails.setupWithViewPager(vpDetails)
        tlDetails.tabMode = TabLayout.MODE_FIXED
    }

    companion object {

        private val TAG = DetailsActivity::class.java.simpleName

        private val EXTRA_HACKER_NEWS_ITEM = "EXTRA_HACKER_NEWS_ITEM"

        fun getCallingIntent(context: Context, hackerNewsItemModel: HackerNewsItemModel): Intent {
            val intent = Intent(context, DetailsActivity::class.java)
            intent.putExtra(EXTRA_HACKER_NEWS_ITEM, hackerNewsItemModel)
            return intent
        }
    }
}
