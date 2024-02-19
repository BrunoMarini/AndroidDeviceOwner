package com.device.owner.sample;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

public class DeviceOwnerReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        // Open the Enroll Activity
        Intent activityLaunchIntent = new Intent(context, MainActivity.class);
        activityLaunchIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(activityLaunchIntent);
    }
}
