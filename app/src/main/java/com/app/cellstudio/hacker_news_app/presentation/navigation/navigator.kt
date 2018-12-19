package com.app.cellstudio.hacker_news_app.presentation.navigation

import android.content.Context
import android.content.Intent
import android.net.Uri
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
}