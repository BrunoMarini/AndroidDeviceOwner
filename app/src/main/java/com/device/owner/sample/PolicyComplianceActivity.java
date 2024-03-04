package com.device.owner.sample;

import android.app.Activity;
import android.os.Bundle;

public class PolicyComplianceActivity extends Activity {
    private static final String TAG = PolicyComplianceActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_policy_compliance);

        findViewById(R.id.btn_accept).setOnClickListener(v -> accept());
    }

    private void accept() {
        setResult(RESULT_OK);
        finish();
    }
}
