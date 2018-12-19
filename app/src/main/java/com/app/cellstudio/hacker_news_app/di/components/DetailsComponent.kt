package com.app.cellstudio.hacker_news_app.di.components

import com.app.cellstudio.hacker_news_app.di.modules.DetailsModule
import com.app.cellstudio.hacker_news_app.presentation.view.activity.DetailsActivity
import dagger.Subcomponent

@Subcomponent(modules = [DetailsModule::class])
interface DetailsComponent {
    fun inject(detailsActivity: DetailsActivity)
}
