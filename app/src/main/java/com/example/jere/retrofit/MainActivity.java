package com.example.jere.retrofit;

import android.content.Intent;
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
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Response;

/**
 * @author jere
 */
public class MainActivity extends AppCompatActivity {
    private final String TAG = "MainActivity";
    private TextView mTextView;
    private Button mButton;
    private Button mNextButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewId();

        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                retrofitCall();
            }
        });

        mNextButton.setOnClickListener(view -> goToSecondActivity());


    }

    private void findViewId() {
        mButton = findViewById(R.id.button);
        mTextView = findViewById(R.id.tv);
        mNextButton = findViewById(R.id.next_button);
    }

    private void retrofitCall() {
        ApiService apiService = ApiWrapper.newInstance().getService();
        Call<ResponseBody> request = apiService.getProfile();

        /* callback function one */
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

        /* callback function two */
        request.enqueue(new RetrofitCallback<ResponseBody>() {
            @Override
            public void onCallSuccess(String responseData, String message) throws IOException, XmlPullParserException, JSONException {
                Log.d(TAG, "onResponse: " + responseData);
                // TODO handle responseData to display
                mTextView.setText(responseData);
            }

            @Override
            public void onCallFailed(String message) {
                Log.d(TAG, "onFailure: " + message);
            }
        });
    }

    private void goToSecondActivity() {
        Intent intent = new Intent(this, SecondActivity.class);
        startActivity(intent);
    }

//    private String handleResponseData(String responseData) {
//        String mString = null;
//
//        return mString;
//    }



}
