package org.yash10019coder.suspectdetectionxml.data

import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response
import timber.log.Timber

class AuthInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = Request.Builder()
            .url(chain.request().url)
            .method(chain.request().method, chain.request().body)


        if (!chain.request().url.toString().contains("login")) {
            request.addHeader("AUTH_TOKEN", "1234567890")
        }

        try {
            Timber.d("Request: %s", request.build().toString())
            return chain.proceed(request.build())
        } catch (e: Exception) {
            Timber.e(e)
            throw e
        }
    }
}
