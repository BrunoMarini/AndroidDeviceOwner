package com.device.owner.sample;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.device.owner.sample.fragments.HomeFragment;
import com.device.owner.sample.util.Logger;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = MainActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Intent intent = getIntent();
        Logger.d(TAG, "Action: " + intent.getAction());
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.container_main, HomeFragment.newInstance(), null)
                .commit();
    }
}