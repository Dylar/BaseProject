package de.bornholdtlee.defaultproject.api.interceptor

import java.io.IOException

import de.bornholdtlee.defaultproject.BuildConfig
import okhttp3.Credentials
import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response

class AddHeaderInterceptor : Interceptor {
    private val userAgent: String
    private val authToken: String

    init {
        this.userAgent = BuildConfig.APPLICATION_ID + "/" + BuildConfig.VERSION_NAME + " (" + System.getProperty("http.agent") + ")"
        this.authToken = Credentials.basic( BuildConfig.AUTH_USER,  BuildConfig.AUTH_PW)
    }

    @Throws(IOException::class)
    override fun intercept(chain: Interceptor.Chain): Response {
        val original = chain.request()
        val builder = original.newBuilder()
        addAuthHeader(builder)
        addUserAgentHeader(builder)
        val request = builder.build()
        return chain.proceed(request)
    }

    private fun addAuthHeader(builder: Request.Builder) {
        builder.header("Authorization", authToken)
    }

    private fun addUserAgentHeader(builder: Request.Builder) {
        builder.header("User-Agent", userAgent)
    }

}