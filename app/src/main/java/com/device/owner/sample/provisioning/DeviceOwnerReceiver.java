package com.device.owner.sample.provisioning;

import android.app.admin.DeviceAdminReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

import com.device.owner.sample.MainActivity;
import com.device.owner.sample.util.Logger;

public class DeviceOwnerReceiver extends DeviceAdminReceiver {
    private static final String TAG = DeviceOwnerReceiver.class.getSimpleName();

    @Override
    public void onEnabled(Context context, Intent intent) {
        Logger.d(TAG, "onEnabled");
        showToast(context, "Admin onEnabled()");
        launchMainActivity(context);
    }

    @Override
    public void onProfileProvisioningComplete(Context context, Intent intent) {
        Logger.d(TAG, "onProfileProvisioningComplete");
        showToast(context, "onProfileProvisioningComplete()");
    }

    private void launchMainActivity(Context context) {
        // Launch Activity after provisioning
        Intent startActivityIntent = new Intent(context, MainActivity.class);
        startActivityIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(startActivityIntent);
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
