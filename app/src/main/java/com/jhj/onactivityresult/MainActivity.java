package com.jhj.onactivityresult;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

import com.jhj.activityresult.ActivityResult;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ActivityResult.init(MainActivity.this)
                        .targetActivity(TestActivity.class)
                        .putString("String", "这是一个Activity")
                        .onResult(new ActivityResult.OnActivityResultListener() {
                            @Override
                            public void onResult(Intent data) {
                                String s = data.getStringExtra("data");
                                Log.w("xxx", s);
                            }
                        });
            }
        });

    }
}
