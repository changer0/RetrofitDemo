package com.example.lulu.retrofitdemo1;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.lang.annotation.Annotation;
import java.lang.reflect.Type;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Converter;
import retrofit2.Response;
import retrofit2.Retrofit;

public class MainActivity extends AppCompatActivity implements Callback<String> {

    private TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Retrofit retrofit = new Retrofit.Builder().baseUrl("httP://www.baidu.com")
                .addConverterFactory(
                        new Converter.Factory() {
                            @Override
                            public Converter<ResponseBody, ?> responseBodyConverter(Type type, Annotation[] annotations, Retrofit retrofit) {
                                return new Converter<ResponseBody, String>() {
                                    @Override
                                    public String convert(ResponseBody value) throws IOException {
                                        return value.string();
                                    }
                                };
                            }
                        }
                )//解析转换
                .build();
        Service service = retrofit.create(Service.class);
        Call<String> call = service.getBaidu();
        call.enqueue(this);
        textView = (TextView) findViewById(R.id.main_text);
    }

    @Override
    public void onResponse(Call<String> call, Response<String> response) {
        textView.setText(response.body());
    }

    @Override
    public void onFailure(Call<String> call, Throwable t) {
        Toast.makeText(MainActivity.this, "请求失败:" + call.request().url(), Toast.LENGTH_SHORT).show();
        t.printStackTrace();
    }
}
