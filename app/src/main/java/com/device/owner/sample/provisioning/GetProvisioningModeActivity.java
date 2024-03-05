package com.device.owner.sample.provisioning;

import static android.app.admin.DevicePolicyManager.EXTRA_PROVISIONING_MODE;
import static android.app.admin.DevicePolicyManager.PROVISIONING_MODE_FULLY_MANAGED_DEVICE;
import static android.app.admin.DevicePolicyManager.PROVISIONING_MODE_MANAGED_PROFILE;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import com.device.owner.sample.R;

public class GetProvisioningModeActivity extends Activity {
    private static final String TAG = GetProvisioningModeActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_get_provisioning_mode);

        findViewById(R.id.btn_enroll_fully_managed_device).setOnClickListener(v -> onDOButtonClick());
        findViewById(R.id.btn_enroll_managed_profile).setOnClickListener(v -> onWPCButtonClick());
    }

    private void onDOButtonClick() {
        final Intent intent = new Intent();
        intent.putExtra(EXTRA_PROVISIONING_MODE, PROVISIONING_MODE_FULLY_MANAGED_DEVICE);
        finishWithIntent(intent);
    }

    private void onWPCButtonClick() {
        final Intent intent = new Intent();
        intent.putExtra(EXTRA_PROVISIONING_MODE, PROVISIONING_MODE_MANAGED_PROFILE);
        finishWithIntent(intent);
    }

    private void finishWithIntent(Intent intent) {
        setResult(RESULT_OK, intent);
        finish();
    }
}
