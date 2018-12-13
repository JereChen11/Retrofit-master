package com.example.jere.retrofit;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.jere.retrofit.api.ApiService;
import com.example.jere.retrofit.api.ApiWrapper;

import org.json.JSONException;
import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;

import okhttp3.Callback;
import okhttp3.Response;
import okhttp3.ResponseBody;
import retrofit2.Call;

/**
 * @author jere
 */
public class MainActivity extends AppCompatActivity {
    private final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button button = (Button) findViewById(R.id.button);
        TextView textView = (TextView) findViewById(R.id.tv);

        ApiService apiService = ApiWrapper.newInstance().getService();
        Call<ResponseBody> typeRequest = apiService.getTypeConfig();

//        typeRequest.enqueue(new retrofit2.Callback<ResponseBody>() {
//            @Override
//            public void onResponse(Call<ResponseBody> call, retrofit2.Response<ResponseBody> response) {
//                String responseData = response.body().toString();
//                Log.d(TAG, "onResponse: " + responseData);
//            }
//
//            @Override
//            public void onFailure(Call<ResponseBody> call, Throwable t) {
//                Log.d(TAG, "onFailure: " + t.getMessage());
//            }
//        });

        typeRequest.enqueue(new RetrofitCallback<ResponseBody>() {
            @Override
            public void onCallSuccess(String responseData, String message) throws IOException, XmlPullParserException, JSONException {
                Log.d(TAG, "onResponse: " + responseData);
            }

            @Override
            public void onCallFailed(String message) {
                Log.d(TAG, "onFailure: " + message);
            }
        });
    }





}
