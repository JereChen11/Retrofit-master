package com.example.jere.retrofit;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.example.jere.retrofit.practiceDemo.PracticeDemo;
import com.example.jere.retrofit.simpleDemo.SimpleDemo;

/**
 * @author jere
 */
public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.d(TAG, "onCreate: ");
        findViewId();

    }

    private void findViewId() {
        Button practiceBtn = findViewById(R.id.practice_demo_btn);
        Button simpleBtn = findViewById(R.id.simple_demo_btn);
        practiceBtn.setOnClickListener(this);
        simpleBtn.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.practice_demo_btn:
                Intent practiceIntent = new Intent(this, PracticeDemo.class);
                startActivity(practiceIntent);
                break;
            case R.id.simple_demo_btn:
                Intent simpleIntent = new Intent(this, SimpleDemo.class);
                startActivity(simpleIntent);
                break;
            default:

        }
    }
}
