package org.yash10019coder.suspectdetectionxml.data

import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response

class AuthInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = Request.Builder()
            .url(Retrofit.BASE_URL+"/login")

        request.addHeader("AUTH_TOKEN", "1234567890")

        try {
            return chain.proceed(request.build())
        } catch (e: Exception) {
            e.printStackTrace()
            throw e
        }

    }
}
