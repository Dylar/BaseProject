package de.bornholdtlee.baseproject.api.interceptor

import java.io.IOException

import de.bornholdtlee.baseproject.BuildConfig
import okhttp3.Credentials
import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response

class AddHeaderInterceptor : Interceptor {
    private val userAgent: String = BuildConfig.APPLICATION_ID +
            "/" + BuildConfig.VERSION_NAME +
            " (" + System.getProperty("http.agent") + ")"
    private val authToken: String = Credentials.basic(BuildConfig.AUTH_USER, BuildConfig.AUTH_PW)

    @Throws(IOException::class)
    override fun intercept(chain: Interceptor.Chain): Response {
        val original = chain.request()
        val builder = original.newBuilder()
        addHeader(builder)
        val request = builder.build()
        return chain.proceed(request)
    }

    private fun addHeader(builder: Request.Builder) {
        builder.header("Authorization", authToken)
        builder.header("UserData-Agent", userAgent)
    }

}