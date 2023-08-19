package com.yagmurceliksoy.bookapp

import android.app.Application
import android.content.Context
import com.chuckerteam.chucker.api.ChuckerInterceptor
import com.yagmurceliksoy.bookapp.common.Constants.BASE_URL
import com.yagmurceliksoy.bookapp.data.source.remote.BookService
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

class MainApplication : Application() {
    companion object {

        var bookService: BookService? = null

        private const val TIMEOUT = 60L

        fun provideRetrofit(context: Context) {

            val chuckerInterceptor = ChuckerInterceptor.Builder(context).build()

            val okHttpClient = OkHttpClient.Builder().apply {
                addInterceptor(chuckerInterceptor)
                readTimeout(TIMEOUT, TimeUnit.SECONDS)
                connectTimeout(TIMEOUT, TimeUnit.SECONDS)
                writeTimeout(TIMEOUT, TimeUnit.SECONDS)
            }.build()

            bookService = Retrofit.Builder().apply {
                addConverterFactory(GsonConverterFactory.create())
                baseUrl(BASE_URL)
                client(okHttpClient)
            }.build().create(BookService::class.java)
        }
    }
}