package com.app.cellstudio.hacker_news_app.presentation.navigation

import android.content.Context
import com.app.cellstudio.hacker_news_app.interactor.model.HackerNewsItemModel
import com.app.cellstudio.hacker_news_app.presentation.view.activity.DetailsActivity
import com.app.cellstudio.hacker_news_app.presentation.view.activity.MainActivity
import javax.inject.Inject
import javax.inject.Singleton


@Singleton
class Navigator @Inject constructor() {

    fun navigateToMain(context: Context?) {
        if (context != null) {
            val intentToLaunch = MainActivity.getCallingIntent(context)
            context.startActivity(intentToLaunch)
        }
    }

    fun navigateToDetails(context: Context?, hackerNewsItemModel: HackerNewsItemModel) {
        if (context != null) {
            val intentToLaunch = DetailsActivity.getCallingIntent(context, hackerNewsItemModel)
            context.startActivity(intentToLaunch)
        }
    }
}