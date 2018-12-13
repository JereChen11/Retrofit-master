package com.example.jere.retrofit;

import okhttp3.Response;

/**
 * @author jere
 */
public interface RequestCallback {

    void onSuccess(Response response, String message);

    void onFailure(String message);

}
