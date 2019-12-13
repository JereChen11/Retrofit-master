package com.example.jere.retrofit.customcallback;

import com.google.gson.Gson;

import java.io.IOException;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public abstract class AbstractGetUserInfoCallback implements Callback<ResponseBody> {
    abstract void getUserInfoSuccess(User user);

    abstract void getUserInfoFailed(String failedMessage);

    @Override
    public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
        if (response.code() == 200) {
            try {
                String responseBodyString = response.body().string();
                Gson gson = new Gson();
                User user = gson.fromJson(responseBodyString, User.class);
                getUserInfoSuccess(user);
            } catch (IOException e) {
                e.printStackTrace();
                getUserInfoFailed("parse response body failed!");
            }
        } else {
            getUserInfoFailed("error responseCode: " + response.code());
        }
    }

    @Override
    public void onFailure(Call<ResponseBody> call, Throwable t) {
        getUserInfoFailed(t.getMessage());
    }
}