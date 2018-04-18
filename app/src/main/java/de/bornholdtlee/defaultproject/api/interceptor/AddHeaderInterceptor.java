package de.bornholdtlee.defaultproject.api.interceptor;

import java.io.IOException;

import de.bornholdtlee.defaultproject.BuildConfig;
import okhttp3.Credentials;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

public class AddHeaderInterceptor implements Interceptor {
    private final String userAgent;
    private final String authToken;

    public AddHeaderInterceptor() {
        this.userAgent = BuildConfig.APPLICATION_ID + "/" + BuildConfig.VERSION_NAME + " (" + System.getProperty("http.agent") + ")";

        //TODO "Temporary basic auth" until Oauth 2.0
        this.authToken = Credentials.basic("tlc-app", "J2xX5dVbT6ReRNbmzJBs");
    }

    @Override
    public Response intercept(Chain chain) throws IOException {
        Request original = chain.request();
        Request.Builder builder = original.newBuilder();
        addAuthHeader(builder);
        addUserAgentHeader(builder);
        Request request = builder.build();
        return chain.proceed(request);
    }

    private void addAuthHeader(Request.Builder builder) {
        builder.header("Authorization", authToken);
    }

    private void addUserAgentHeader(Request.Builder builder) {
        builder.header("User-Agent", userAgent);
    }

}