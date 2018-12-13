package com.example.jere.retrofit;

import org.json.JSONException;
import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * @author jere
 */
public abstract class SimpleRetrofitCallback implements Callback<ResponseBody> {

    @Override
    public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
        try {
            if (response.code() != 200) {
                onFailure("call failed");
            } else {
                String responseData = ((ResponseBody) response.body()).string();
                onSuccess(responseData, "call success");
            }
        } catch (IOException | XmlPullParserException | JSONException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onFailure(Call<ResponseBody> call, Throwable t) {

    }

    public abstract void onSuccess(String responseData, String message) throws IOException, XmlPullParserException, JSONException;

    public abstract void onFailure(String message);

}
