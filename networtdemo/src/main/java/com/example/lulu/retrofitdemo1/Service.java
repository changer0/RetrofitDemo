package com.example.lulu.retrofitdemo1;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by lulu on 2016/11/23.
 */

public interface Service {
    @GET("/")
    Call<String> getBaidu();
}
