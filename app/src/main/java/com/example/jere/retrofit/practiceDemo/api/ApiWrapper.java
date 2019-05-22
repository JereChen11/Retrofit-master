package com.example.jere.retrofit.practiceDemo.api;

import com.example.jere.retrofit.practiceDemo.ApiWrapperBase;

/**
 * @author jere
 * @date 12/12/2018
 */
public class ApiWrapper extends ApiWrapperBase {
    protected ApiWrapper() {
        super();
        mService = mRetrofit.create(ApiService.class);
    }

    public static ApiWrapper newInstance() {
        return new ApiWrapper();
    }

    @Override
    public ApiService getService() {
        return (ApiService) mService;
    }


    @Override
    protected String getApiHost() {
        return "https://blog.csdn.net";
    }
}
