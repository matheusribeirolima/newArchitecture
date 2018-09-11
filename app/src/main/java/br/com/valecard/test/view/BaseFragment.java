package br.com.valecard.test.view;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import br.com.valecard.test.viewmodel.BaseViewModel;

public abstract class BaseFragment<T extends ViewDataBinding, V extends BaseViewModel> extends Fragment {

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

    public BaseActivity getBaseActivity() {
        return baseActivity;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof BaseActivity) {
            BaseActivity baseActivity = (BaseActivity) context;
            this.baseActivity = baseActivity;
            baseActivity.onFragmentAttached();
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

    public void hideKeyboard() {
        if (baseActivity != null) {
            baseActivity.hideKeyboard();
        }
    }

    public boolean isNetworkConnected() {
        return baseActivity != null && baseActivity.isNetworkConnected();
    }

    public interface Callback {

        void onFragmentAttached();

        void onFragmentDetached(String tag);
    }
}
