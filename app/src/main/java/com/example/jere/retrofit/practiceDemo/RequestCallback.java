package com.example.jere.retrofit.practiceDemo;

import okhttp3.Response;

/**
 * @author jere
 */
public interface RequestCallback {

    void onSuccess(Response response, String message);

    void onFailure(String message);

}
