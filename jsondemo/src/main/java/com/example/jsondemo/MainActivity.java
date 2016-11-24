package com.example.jsondemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity implements Callback<Tngou> {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://www.tngou.net")
                 .addConverterFactory(GsonConverterFactory.create())
                .build();
        Service service = retrofit.create(Service.class);
        Call<Tngou> call = service.getList(0, 1, 5);
        call.enqueue(this);
    }

    @Override
    public void onResponse(Call<Tngou> call, Response<Tngou> response) {
        Tngou tngou = response.body();
        List<Cook> list = tngou.getList();

    }

    @Override
    public void onFailure(Call<Tngou> call, Throwable t) {
        t.printStackTrace();
    }
}
