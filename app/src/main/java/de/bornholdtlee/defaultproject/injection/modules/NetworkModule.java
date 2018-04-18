package de.bornholdtlee.defaultproject.injection.modules;

import com.facebook.stetho.okhttp3.StethoInterceptor;

import dagger.Module;
import dagger.Provides;
import de.bornholdtlee.defaultproject.BuildConfig;
import de.bornholdtlee.defaultproject.api.RetrofitInterface;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

import static de.bornholdtlee.defaultproject.BuildConfig.BASE_URL;

@Module
public class NetworkModule {

    @Provides
    @ApplicationScope
    public OkHttpClient provideOkHttpClient() {
        OkHttpClient.Builder builder = new OkHttpClient.Builder();

        if (BuildConfig.DEBUG) {
            HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
            loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

            builder.addInterceptor(loggingInterceptor);
            builder.addNetworkInterceptor(new StethoInterceptor());
        }

        return builder.build();
    }

    @Provides
    @ApplicationScope
    public RetrofitInterface provideRetrofit(OkHttpClient okHttpClient) {
        Retrofit.Builder retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create());

        if (BuildConfig.DEBUG) {
            retrofit.client(okHttpClient);
        }

        return retrofit.build().create(RetrofitInterface.class);
    }
}