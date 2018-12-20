package com.example.jere.retrofit;

import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;

/**
 * @author jere
 */
public class SecondActivity extends AppCompatActivity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.second_activity_layout);

        Button nextButton = findViewById(R.id.go_to_third_activity_button);
        nextButton.setOnClickListener(v -> goToThirdActivity());
    }

    private void goToThirdActivity() {
        Intent mIntent = new Intent(this, ThirdActivity.class);
        startActivity(mIntent);
    }
}
