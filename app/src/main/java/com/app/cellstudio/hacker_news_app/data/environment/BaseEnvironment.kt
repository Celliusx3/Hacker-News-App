package com.app.cellstudio.data.environment

class BaseEnvironment : Environment {
    override fun getHackerNewsUrl(): String {
        return "https://hn.algolia.com/api/v1/"
    }

    override fun getHackerNewsFirebaseUrl(): String {
        return "https://hacker-news.firebaseio.com/v0/"
    }
}