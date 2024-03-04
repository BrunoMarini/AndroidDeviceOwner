package com.device.owner.sample.util;

public class Logger {
    private static final String APP_TAG = "[DeviceOwnerSample] ";

    public static void d(String TAG, String message) {
        android.util.Log.d(APP_TAG, buildMessage(TAG, message));
    }

    public static void e(String TAG, String message, Throwable throwable) {
        android.util.Log.e(APP_TAG, buildMessage(TAG, message), throwable);
    }

    private static String buildMessage(String TAG, String message) {
        return TAG + ": " + message;
    }
}
