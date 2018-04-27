package de.bornholdtlee.defaultproject.injection.modules

import com.facebook.stetho.okhttp3.StethoInterceptor
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import de.bornholdtlee.defaultproject.BuildConfig
import de.bornholdtlee.defaultproject.api.RetrofitInterface
import de.bornholdtlee.defaultproject.api.interceptor.AddHeaderInterceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
class NetworkModule {

    companion object {

        private val BASE_URL = "https://www.stromnetz.hamburg/"
        private val BASE_URL_DISTURBANCE = "https://stoerungskarte.stromnetz-hamburg.de/"
        private val BASE_URL_CONSUMPTION = "http://www.energieportal-hamburg.de/distribution/energieportal/"
    }

    @Provides
    @Singleton
    fun provideGsonBuilder(): GsonBuilder {
        val gsonBuilder = GsonBuilder()
//        gsonBuilder.registerTypeAdapter(CheckMail::class.java, CheckMailConverter())
        return gsonBuilder
    }

    @Provides
    @Singleton
    fun provideOkHttpClientBuilder(): OkHttpClient.Builder {
        val builder = OkHttpClient.Builder()

        if (BuildConfig.DEBUG) {
            val loggingInterceptor = HttpLoggingInterceptor()
            loggingInterceptor.level = HttpLoggingInterceptor.Level.HEADERS
            loggingInterceptor.level = HttpLoggingInterceptor.Level.BODY

            builder.addInterceptor(AddHeaderInterceptor())
                    .addNetworkInterceptor(StethoInterceptor())
                    .addNetworkInterceptor(loggingInterceptor)
        }

        return builder
    }

    @Provides
    @Singleton
    fun provideRetrofitBuilder(gsonBuilder: GsonBuilder): Retrofit.Builder {
        return Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(gsonBuilder.create()))
    }

    @Provides
    @Singleton
    fun provideRetrofitConstructions(retrofitBuilder: Retrofit.Builder, httpClientBuilder: OkHttpClient.Builder): RetrofitInterface {

        if (BuildConfig.IS_DEV) {
            retrofitBuilder.client(httpClientBuilder.build())
        }

        return retrofitBuilder.build().create(RetrofitInterface::class.java)
    }

}
