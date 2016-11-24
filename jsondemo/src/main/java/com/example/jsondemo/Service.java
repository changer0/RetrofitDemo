package com.example.jsondemo;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by lulu on 2016/11/24.
 */

public interface Service {
    @GET("/api/{category}/list") //get用户获得数据
    Call<Tngou> getList(@Path("category") String category,
                        @Query("id") int id,
                        @Query("page") int page,
                        @Query("rows") int rows);
    @POST("/api/{category}/list") //post用户上传数据
    @FormUrlEncoded
    Call<Tngou> postList(@Path("category") String category,
                        @Field("id") int id,
                        @Field("page") int page,
                        @Field("rows") int rows);
}
