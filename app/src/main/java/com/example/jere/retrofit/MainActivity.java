package com.example.jere.retrofit;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button button = (Button) findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ApiService apiService = ApiWrapper.newInstance().getService();
                Call<ResponseBody> typeRequest = apiService.getTypeConfig();
                typeRequest.enqueue(new RetrofitCallback<ResponseBody>() {
                    @Override
                    public void onSuccess(String responseData, String message) throws IOException, XmlPullParserException, JSONException {
                        Log.d("onSuccess: ", "" + responseData);
                    }
                });
            }
        });


    }




}
