package com.example.jere.retrofit.simpleDemo.api;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;

/**
 * @author jere
 */
public class SimpleDemoApiWrapper {
    private static Retrofit retrofit;
    private static final String BASE_URL = "https://blog.csdn.net";

    public static Retrofit getRetrofitInstance() {
        if (retrofit == null) {

            // customize retrofit network timeouts, default timeouts is ten seconds.
            OkHttpClient okHttpClient = new OkHttpClient.Builder()
                    .connectTimeout(1, TimeUnit.MINUTES)
                    .readTimeout(30, TimeUnit.SECONDS)
                    .writeTimeout(15, TimeUnit.SECONDS)
                    .build();

            retrofit = new retrofit2.Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .client(okHttpClient)
                    .build();
        }
        return retrofit;
    }
}
