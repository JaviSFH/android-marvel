package com.javnez.marvel.core

import okhttp3.Interceptor
import okhttp3.Interceptor.Chain
import okhttp3.Request
import okhttp3.Response
import java.math.BigInteger
import java.security.MessageDigest
import javax.inject.Inject

class MarvelNetworkInterceptor @Inject constructor() : Interceptor {

    companion object {
        const val API_PUBLIC_KEY = "f2e5d2bbc01886c404d86c8e467e2892"
        const val API_PRIVATE_KEY = "d162939a655349aa16419ff39095f45f7519e763"
    }

    override fun intercept(chain: Chain): Response {

        val currentTimestamp = System.currentTimeMillis().toString()

        val original = chain.request()
        val originalHttpUrl = original.url

        val url = originalHttpUrl.newBuilder()
            .addQueryParameter("ts", currentTimestamp)
            .addQueryParameter("apikey", API_PUBLIC_KEY)
            .addQueryParameter("hash", buildHash(currentTimestamp))
            .build()

        // Request customization: add request headers
        val requestBuilder: Request.Builder = original.newBuilder()
            .url(url)

        val request: Request = requestBuilder.build()
        return chain.proceed(request)
    }

    private fun buildHash(textTimeStamp: String): String {
        val chain = "$textTimeStamp$API_PRIVATE_KEY$API_PUBLIC_KEY"
        val md = MessageDigest.getInstance("MD5")

        return BigInteger(1, md.digest(chain.toByteArray())).toString(16).padStart(32, '0')
    }
}