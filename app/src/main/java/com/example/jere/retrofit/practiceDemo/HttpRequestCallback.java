package com.example.jere.retrofit.practiceDemo;

import org.json.JSONException;
import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;

import okhttp3.Response;

/**
 * @author jere
 */
public abstract class HttpRequestCallback implements RequestCallback {

    @Override
    public void onSuccess(Response response, String message) {
        try {
            String responseData = response.body().string();
            onCallSuccess(responseData, message);
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
        onCallFailed(message);
    }

    public abstract void onCallSuccess(String responseData, String message) throws IOException, XmlPullParserException, JSONException;

    public abstract void onCallFailed(String message);
}
