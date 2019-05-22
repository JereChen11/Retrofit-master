package com.example.jere.retrofit.simpleDemo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.jere.retrofit.R;
import com.example.jere.retrofit.simpleDemo.api.SimpleDemoApiService;
import com.example.jere.retrofit.simpleDemo.api.SimpleDemoApiWrapper;

import java.io.IOException;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * @author jere
 */
public class SimpleDemo extends AppCompatActivity {
    private static final String TAG = "SimpleDemo";
    private TextView mTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_simple_demo);


        Button callApiBtn = findViewById(R.id.simple_demo_call_api_btn);
        mTextView = findViewById(R.id.simple_demo_tv);
        callApiBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getBlog();
            }
        });

    }

    private void getBlog() {
        SimpleDemoApiService simpleDemoApiService = SimpleDemoApiWrapper.getRetrofitInstance().create(SimpleDemoApiService.class);
        simpleDemoApiService.getJereChenBlog().enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                Log.d(TAG, "onResponse: ");
                try {
                    mTextView.setText(response.body().string());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Log.d(TAG, "onFailure: ");
            }
        });
    }

}
