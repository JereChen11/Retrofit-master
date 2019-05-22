package com.example.jere.retrofit.practiceDemo;

import com.example.jere.retrofit.practiceDemo.HttpRequestCallback;

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
public abstract class RetrofitCallback<T> extends HttpRequestCallback implements Callback<T> {

    public RetrofitCallback() {

    }

    @Override
    public void onResponse(Call<T> call, Response<T> response) {
        try {
            if (response.code() != 200) {
                String responseData = ((ResponseBody) response.errorBody()).string();
                onFailure(responseData);
            } else {
                String responseData = ((ResponseBody) response.body()).string();
                onCallSuccess(responseData, "");
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (XmlPullParserException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onFailure(Call<T> call, Throwable t) {
        onFailure(t.getMessage());
    }

//    @Override
//    public abstract void onCallSuccess(String responseData, String message) throws IOException, XmlPullParserException, JSONException;
}
