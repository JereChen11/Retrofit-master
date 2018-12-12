package com.example.jere.retrofit;

import org.json.JSONException;
import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;

import okhttp3.Response;

/**
 * @author jere
 */
public abstract class HttpRequestCallback implements RequestCalback{

    @Override
    public void onSuccess(Response response, String message) {
        try {
            String responseData = response.body().string();
            onSuccess(responseData, message);
            response.close();
        } catch (IOException e) {

        } catch (XmlPullParserException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        } finally {
            response.body().close();
        }
    }

    @Override
    public void onFailure(String message) {

    }

    public abstract void onSuccess(String responseData, String message) throws IOException, XmlPullParserException, JSONException;
}
