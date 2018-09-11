package br.com.valecard.test.view;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import br.com.valecard.test.viewmodel.BaseViewModel;

public abstract class BaseDialog<T extends ViewDataBinding, V extends BaseViewModel> extends DialogFragment {

    private BaseActivity baseActivity;
    private T dataBinding;
    private V viewModel;

    public abstract int getBindingVariable();

    @LayoutRes
    public abstract int getLayoutId();

    public abstract V getViewModel();

    public abstract void initBinding();

    public T getDataBinding() {
        return dataBinding;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof BaseActivity) {
            BaseActivity activity = (BaseActivity) context;
            baseActivity = activity;
            activity.onFragmentAttached();
        }
    }

    @Override
    public void onDetach() {
        baseActivity = null;
        super.onDetach();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        viewModel = getViewModel();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        dataBinding = DataBindingUtil.inflate(inflater, getLayoutId(), container, false);
        return dataBinding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        dataBinding.setVariable(getBindingVariable(), viewModel);
        dataBinding.executePendingBindings();

        initBinding();
    }

    public void show(FragmentManager fragmentManager, String tag) {
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        Fragment prevFragment = fragmentManager.findFragmentByTag(tag);
        if (prevFragment != null) {
            transaction.remove(prevFragment);
        }
        transaction.addToBackStack(null);
        show(transaction, tag);
    }

    public void dismissDialog(String tag) {
        dismiss();
        getBaseActivity().onFragmentDetached(tag);
    }

    public BaseActivity getBaseActivity() {
        return baseActivity;
    }

    public void hideKeyboard() {
        if (baseActivity != null) {
            baseActivity.hideKeyboard();
        }
    }

    public void showLoading() {
        if (baseActivity != null) {
            baseActivity.showLoading();
        }
    }

    public void hideLoading() {
        if (baseActivity != null) {
            baseActivity.hideLoading();
        }
    }

    public boolean isNetworkConnected() {
        return baseActivity != null && baseActivity.isNetworkConnected();
    }
}
