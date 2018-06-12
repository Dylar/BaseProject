package de.bornholdtlee.baseproject.injection.modules

import com.facebook.stetho.okhttp3.StethoInterceptor
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import de.bornholdtlee.baseproject.BuildConfig
import de.bornholdtlee.baseproject.api.RetrofitInterface
import de.bornholdtlee.baseproject.api.interceptor.AddHeaderInterceptor
import de.bornholdtlee.baseproject.injection.ApplicationScope
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

@Module
class NetworkModule {

    companion object {
        private val BASE_URL = "https://api.stackexchange.com"
    }

    @Provides
    @ApplicationScope
    fun provideGsonBuilder(): GsonBuilder {
        val gsonBuilder = GsonBuilder()
//        gsonBuilder.registerTypeAdapter(CheckMail::class.java, CheckMailConverter())
        return gsonBuilder
    }

    @Provides
    @ApplicationScope
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
    @ApplicationScope
    fun provideRetrofitBuilder(gsonBuilder: GsonBuilder): Retrofit.Builder {
        return Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create(gsonBuilder.create()))
    }

    @Provides
    @ApplicationScope
    fun provideRetrofitConstructions(retrofitBuilder: Retrofit.Builder, httpClientBuilder: OkHttpClient.Builder): RetrofitInterface {

        if (BuildConfig.IS_DEV) {
            retrofitBuilder.client(httpClientBuilder.build())
        }

        return retrofitBuilder.build().create(RetrofitInterface::class.java)
    }

}
