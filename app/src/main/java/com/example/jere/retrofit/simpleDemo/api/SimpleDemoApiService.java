package com.example.jere.retrofit.simpleDemo.api;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;

/**
 * @author jere
 */
public interface SimpleDemoApiService {

    @GET("/jerechen/article/details/83722574")
    Call<ResponseBody> getJereChenBlog();
}
