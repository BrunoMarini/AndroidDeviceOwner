package com.device.owner.sample;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.app.admin.DevicePolicyManager;
import android.os.Bundle;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Show current device status
        ((TextView) findViewById(R.id.tv_device_owner_status))
                .setText(getDeviceOwnerStatusMessage());
    }

    private String getDeviceOwnerStatusMessage() {
        DevicePolicyManager dpm = (DevicePolicyManager) getSystemService(Activity.DEVICE_POLICY_SERVICE);
        if (dpm == null) {
            return getString(R.string.error_dpm_null);
        }

        if (dpm.isDeviceOwnerApp(getPackageName())) {
            return getString(R.string.default_true);
        }

        return getString(R.string.default_false);
    }
}