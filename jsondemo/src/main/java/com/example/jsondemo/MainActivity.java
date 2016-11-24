package com.example.jsondemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity implements Callback<Tngou> {

    private MyAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://www.tngou.net")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        Service service = retrofit.create(Service.class);
        Call<Tngou> call = service.postList("cook", 0, 1, 5);
        call.enqueue(this);
        ListView listView = (ListView) findViewById(R.id.main_list);
        adapter = new MyAdapter(this, new ArrayList<Cook>());
        listView.setAdapter(adapter);
    }

    @Override
    public void onResponse(Call<Tngou> call, Response<Tngou> response) {
        Tngou tngou = response.body();
        List<Cook> list = tngou.getList();
        adapter.addAll(list);
    }

    @Override
    public void onFailure(Call<Tngou> call, Throwable t) {
        t.printStackTrace();
    }
}
