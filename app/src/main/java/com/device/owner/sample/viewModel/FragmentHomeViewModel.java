package com.device.owner.sample.viewModel;

import android.app.Application;
import android.app.admin.DevicePolicyManager;
import android.content.ComponentName;
import android.content.Context;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.device.owner.sample.DeviceOwnerReceiver;
import com.device.owner.sample.R;
import com.device.owner.sample.util.Logger;

public class FragmentHomeViewModel extends AndroidViewModel {
    private static final String TAG = FragmentHomeViewModel.class.getSimpleName();

    private final DevicePolicyManager mDpm;
    private final ComponentName mAdminComponent;

    private MutableLiveData<String> mProfileStatusLiveData;
    private MutableLiveData<Boolean> mUsbDataSignalLiveData;

    public LiveData<String> onProfileStatusChanged() {
        if (mProfileStatusLiveData == null) mProfileStatusLiveData = new MutableLiveData<>();
        return mProfileStatusLiveData;
    }

    public LiveData<Boolean> onUsbDataSignalChanged() {
        if (mUsbDataSignalLiveData == null) mUsbDataSignalLiveData = new MutableLiveData<>();
        return mUsbDataSignalLiveData;
    }

    public FragmentHomeViewModel(@NonNull Application application) {
        super(application);
        Context context = getApplication().getApplicationContext();
        mAdminComponent = DeviceOwnerReceiver.getAdminComponent(context);
        mDpm = (DevicePolicyManager) context.getSystemService(Context.DEVICE_POLICY_SERVICE);
    }

    public void startUi() {
        Logger.d(TAG, "startUi()");
        verifyAppEnrolled();
        mUsbDataSignalLiveData.setValue(mDpm.isUsbDataSignalingEnabled());
    }

    public void onUsbDataSignalPressed() {
        try {
            boolean isEnabled = mDpm.isUsbDataSignalingEnabled();
            mDpm.setUsbDataSignalingEnabled(!isEnabled);
            boolean result = mDpm.isUsbDataSignalingEnabled();
            mUsbDataSignalLiveData.setValue(result);
            showToast("USB data signal set: " + result);
            Logger.d(TAG, "USB data signal was " + isEnabled + " and now " + result);
        } catch (SecurityException | IllegalStateException e) {
            Logger.e(TAG, "Failed to setUsbDataSignalingEnabled", e);
            showToast("Exception calling setUsbDataSignalingEnabled");
        }
    }

    private void verifyAppEnrolled() {
        String message = getApplication().getString(R.string.app_not_managed);
        String myPackage = getApplication().getPackageName();
        if (mDpm.isDeviceOwnerApp(myPackage)) {
            message = getApplication().getString(R.string.app_is_do);
        } else if (mDpm.isOrganizationOwnedDeviceWithManagedProfile()) {
            message = getApplication().getString(R.string.app_is_wpc);
        }
        mProfileStatusLiveData.setValue(message);
    }

    private void showToast(String message) {
        Context context = getApplication().getApplicationContext();
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
    }
}
