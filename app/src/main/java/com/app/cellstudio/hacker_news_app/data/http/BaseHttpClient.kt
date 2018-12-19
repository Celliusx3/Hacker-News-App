package com.app.cellstudio.data.http

import android.util.Log.INFO
import com.app.cellstudio.hacker_news_app.data.api.HackerNewsFirebaseService
import com.app.cellstudio.hacker_news_app.data.api.HackerNewsService
import okhttp3.OkHttpClient
import okhttp3.internal.platform.Platform
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

class BaseHttpClient(private val environment: com.app.cellstudio.data.environment.Environment) : HttpClient {

    private val okHttpClient: OkHttpClient
    private lateinit var hackerNewsFirebaseService: HackerNewsFirebaseService
    private lateinit var hackerNewsService: HackerNewsService


    init {
        okHttpClient = createOkHttpClient()
        createServices(okHttpClient)
    }

    override fun getHackerNewsFirebaseApiService(): HackerNewsFirebaseService {
        return hackerNewsFirebaseService
    }

    override fun getHackerNewsApiService(): HackerNewsService {
        return hackerNewsService
    }

    private fun createOkHttpClient(): OkHttpClient {
        val logging = HttpLoggingInterceptor { message -> Platform.get().log(INFO, message, null) }
        logging.level = HttpLoggingInterceptor.Level.BODY

        return OkHttpClient.Builder()
                .connectTimeout(60, TimeUnit.SECONDS)
                .readTimeout(60, TimeUnit.SECONDS)
                .writeTimeout(60, TimeUnit.SECONDS)
                .addInterceptor(logging)
                .build()
    }

    private fun createServices(okHttpClient: OkHttpClient) {
        hackerNewsFirebaseService = createHackerNewsFirebaseApiService(okHttpClient)
        hackerNewsService = createHackerNewsApiService(okHttpClient)
    }

    private fun createHackerNewsFirebaseApiService(client: OkHttpClient): HackerNewsFirebaseService {
        val baseUrl = environment.getHackerNewsFirebaseUrl()

        val retrofit = Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .client(client)
                .build()

        return retrofit.create(HackerNewsFirebaseService::class.java)
    }

    private fun createHackerNewsApiService(client: OkHttpClient): HackerNewsService {
        val baseUrl = environment.getHackerNewsUrl()

        val retrofit = Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .client(client)
            .build()

        return retrofit.create(HackerNewsService::class.java)
    }
}