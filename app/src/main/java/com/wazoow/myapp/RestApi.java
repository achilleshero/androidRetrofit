package com.wazoow.myapp;


import java.util.List;

import retrofit.Callback;
import retrofit.client.Response;
import retrofit.http.Body;
import retrofit.http.Headers;
import retrofit.http.POST;

/**
 * Created by swordmaster on 3/3/2016.
 * Define rest API
 */
public interface RestApi {

    @Headers("Content-Type: application/json")
    @POST("/create")
    void createUser(@Body User user, Callback<ReceiveData> responseCallback);
}