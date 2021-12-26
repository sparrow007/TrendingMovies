package com.androidxlab.dependencyinjection.utils

import android.content.Context
import com.androidxlab.dependencyinjection.R
import okhttp3.Interceptor
import okhttp3.Response
import javax.inject.Inject

class AuthInterceptor @Inject constructor(private val context: Context) : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val url = chain.request().url().newBuilder()
            .addQueryParameter("api_key", context.getString(R.string.api_key)).build()
        val original = chain.request().newBuilder().url(url).build()
        return chain.proceed(original)
    }
}