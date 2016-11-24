package com.example.jsondemo;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by lulu on 2016/11/24.
 */

public interface Service {
    @GET("/api/{category}/list")
    Call<Tngou> getList(@Path("category") String category,
                        @Query("id") int id,
                        @Query("page") int page,
                        @Query("rows") int rows);
}
