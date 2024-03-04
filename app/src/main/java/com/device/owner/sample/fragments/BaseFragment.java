package com.device.owner.sample.fragments;

import android.os.Bundle;
import android.view.View;

import androidx.annotation.LayoutRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.device.owner.sample.util.Logger;
import com.device.owner.sample.R;

import java.util.function.Function;

abstract class BaseFragment<Binding> extends Fragment {
    private static final String TAG = BaseFragment.class.getSimpleName();

    private Binding mBinding;
    private final Function<View, Binding> mBindingGenerator;

    BaseFragment(@LayoutRes int contentLayoutId, Function<View, Binding> bindingGenerator) {
        super(contentLayoutId);
        mBindingGenerator = bindingGenerator;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mBinding = mBindingGenerator.apply(view);
        onBindCreated(mBinding);
    }

    abstract void onBindCreated(Binding binding);

    Binding getBinding() {
        return mBinding;
    }

    void changeFragment(Class<? extends Fragment> fragment) {
        try {
            Fragment fragmentInstance = fragment.newInstance();
            getParentFragmentManager().beginTransaction()
                    .replace(R.id.container_main, fragmentInstance, null)
                    .addToBackStack(null)
                    .commit();
        } catch (IllegalAccessException | java.lang.InstantiationException e) {
            Logger.e(TAG, "Error changing fragment", e);
        }
    }

    void popBackStack() {
        getParentFragmentManager().popBackStack();
    }

    @Override
    public void onDestroyView() {
        mBinding = null;
        super.onDestroyView();
    }
}
