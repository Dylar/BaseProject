package de.bornholdtlee.defaultproject.injection.modules;

import com.facebook.stetho.okhttp3.StethoInterceptor;
import com.google.gson.GsonBuilder;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import de.bornholdtlee.defaultproject.BuildConfig;
import de.bornholdtlee.defaultproject.api.interceptor.AddHeaderInterceptor;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

@Module
public class NetworkModule {

    public static final String BASE_URL = "https://api.iotcloud.service.deutschebahn.com/";

    @Provides
    @Singleton
    public Retrofit.Builder provideRetrofitBuilder() {
        GsonBuilder gsonBuilder = new GsonBuilder();
//        gsonBuilder.registerTypeAdapter(Installation.class, new InstallationConverter());

        return new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(gsonBuilder.create()));
    }

    @Provides
    @Singleton
    public OkHttpClient.Builder provideOkHttpClientBuilder() {
        OkHttpClient.Builder builder = new OkHttpClient.Builder();

        builder.addInterceptor(new AddHeaderInterceptor());

        if (BuildConfig.DEBUG) {
            HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
            loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.HEADERS);
            loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

            builder.addNetworkInterceptor(new StethoInterceptor())
                    .addNetworkInterceptor(loggingInterceptor);
        }

        return builder;
    }

//    @Provides
//    @Singleton
//    public ApiInterface.BaseApi provideThingApi(Retrofit.Builder retroBuilder, OkHttpClient.Builder clientBuilder) {
//        retroBuilder.client(clientBuilder.build());
//        return retroBuilder.build().create(ApiInterface.BaseApi.class);
//    }


}
