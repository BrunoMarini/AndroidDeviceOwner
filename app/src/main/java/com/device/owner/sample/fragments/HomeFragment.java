package com.device.owner.sample.fragments;

import android.os.Bundle;

import androidx.lifecycle.ViewModelProvider;

import com.device.owner.sample.R;
import com.device.owner.sample.databinding.FragmentHomeBinding;
import com.device.owner.sample.viewModel.FragmentHomeViewModel;

public class HomeFragment extends BaseFragment<FragmentHomeBinding> {

    private FragmentHomeViewModel mViewModel;

    private HomeFragment() {
        super(R.layout.fragment_home, FragmentHomeBinding::bind);
    }

    public static HomeFragment newInstance() {
        return new HomeFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    void onBindCreated(FragmentHomeBinding fragmentHomeBinding) {
        mViewModel = new ViewModelProvider(this).get(FragmentHomeViewModel.class);
        observeLiveData();
        getBinding().btnUsbDataSignal.setOnClickListener(v -> mViewModel.onUsbDataSignalPressed());
        mViewModel.startUi();
    }

    private void observeLiveData() {
        mViewModel.onProfileStatusChanged().observe(this, this::setProfileStatusMessage);
        mViewModel.onUsbDataSignalChanged().observe(this, this::updateUsbDataSignal);
    }

    private void setProfileStatusMessage(String message) {
        getBinding().tvAppDoStatus.setText(message);
    }

    private void updateUsbDataSignal(boolean status) {
        getBinding().btnUsbDataSignal.setText(getString(R.string.btn_toggle_usb_data_signal, !status));
    }
}