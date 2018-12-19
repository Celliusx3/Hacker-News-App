package com.app.cellstudio.hacker_news_app.data.api

import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query

interface HackerNewsFirebaseService {
    @GET(ApiRoutes.TOP_STORIES)
    fun getTopStories(@Query("orderBy") orderBy : String = "\"\$key\"" ,
                @Query("limitToFirst") pageSize: Int
    ): Observable<List<Int>>
}