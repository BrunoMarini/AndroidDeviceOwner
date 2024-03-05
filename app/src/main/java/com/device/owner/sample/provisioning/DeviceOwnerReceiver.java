package com.device.owner.sample.provisioning;

import android.app.admin.DeviceAdminReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

import com.device.owner.sample.util.Logger;

public class DeviceOwnerReceiver extends DeviceAdminReceiver {
    private static final String TAG = DeviceOwnerReceiver.class.getSimpleName();

    @Override
    public void onEnabled(Context context, Intent intent) {
        Logger.d(TAG, "onEnabled(): " + intent.getAction());
        showToast(context, "onEnabled()");
    }

    @Override
    public void onProfileProvisioningComplete(Context context, Intent intent) {
        Logger.d(TAG, "onProfileProvisioningComplete(): " + intent.getAction());
        showToast(context, "onProfileProvisioningComplete()");
    }

    private void showToast(Context context, String message) {
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
    }

    /**
     * Get Admin ComponentName
     * @return Admin ComponentName
     */
    public static ComponentName getAdminComponent(Context context) {
        return new ComponentName(context, DeviceOwnerReceiver.class);
    }

}
