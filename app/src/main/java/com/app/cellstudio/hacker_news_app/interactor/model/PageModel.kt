package com.app.cellstudio.hacker_news_app.interactor.model

class PageModel(val title: String,
           val pageId: Int) {
    companion object {
        val ArticlePage = PageModel("Article", 1000)
        val CommentsPage = PageModel("Comments", 1001)
    }
}
