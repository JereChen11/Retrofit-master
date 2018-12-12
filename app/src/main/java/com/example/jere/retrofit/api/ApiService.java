package com.example.jere.retrofit.api;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;

/**
 * @author jere
 * @date 12/12/2018
 */
public interface ApiService {

    @GET("/api/ebadge/ebadge_types_config")
    Call<ResponseBody> getTypeConfig();
}
