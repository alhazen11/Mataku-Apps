package com.apps.mataku.network

import com.apps.mataku.BuildConfig
import okhttp3.Interceptor
import okhttp3.Response
import android.R.attr.host
import android.R.attr.scheme
import okhttp3.HttpUrl



/**
 * Created by root on 11/15/17.
 */
internal class ApiKeyInterceptor : Interceptor{

    override fun intercept(chain: Interceptor.Chain): Response {
        val originalRequest = chain.request()
        val originalUrl = originalRequest.url()
        val url = originalUrl.newBuilder().build()
        val requestBuilder = originalRequest.newBuilder().url(url)
        val request = requestBuilder.build()
        return chain.proceed(request)
    }
}