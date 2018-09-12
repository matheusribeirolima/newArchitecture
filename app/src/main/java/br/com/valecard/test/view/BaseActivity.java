package br.com.valecard.test.view;

import android.content.Context;
import android.content.pm.PackageManager;
import android.databinding.DataBindingUtil;
import android.databinding.Observable;
import android.databinding.ViewDataBinding;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Toast;

import br.com.valecard.test.viewmodel.BaseViewModel;

public abstract class BaseActivity<T extends ViewDataBinding, V extends BaseViewModel>
        extends AppCompatActivity implements BaseFragment.Callback {

    private static final int PERMISSIONS_REQUEST_CODE = 0;
    private RequestPermissionListener requestPermissionListener;

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
    public void onFragmentAttached() {

    }

    @Override
    public void onFragmentDetached(String tag) {

    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        dataBinding = DataBindingUtil.setContentView(this, getLayoutId());
        viewModel = viewModel == null ? getViewModel() : viewModel;
        dataBinding.setVariable(getBindingVariable(), viewModel);
        dataBinding.executePendingBindings();

        viewModel.getShowLoading().addOnPropertyChangedCallback(new Observable.OnPropertyChangedCallback() {
            @Override
            public void onPropertyChanged(Observable sender, int propertyId) {
                if (viewModel.getShowLoading().get()) {
                    Toast.makeText(getApplicationContext(), "Show loading", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(getApplicationContext(), "Hide loading", Toast.LENGTH_SHORT).show();
                }
            }
        });

        initBinding();
    }

    public void hideKeyboard() {
        View view = getCurrentFocus();
        if (view != null) {
            InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            if (imm != null) {
                imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
            }
        }
    }

    public void showLoading() {
        //progressDialog = CommonUtils.showLoadingDialog(this);
    }

    public void hideLoading() {
//        if (progressDialog != null && progressDialog.isShowing()) {
//            progressDialog.cancel();
//        }
    }

    public boolean isNetworkConnected() {
        //return NetworkUtils.isNetworkConnected(getApplicationContext());
        return true;
    }

    public void requestPermission(String permission,
                                  RequestPermissionListener requestPermissionListener) {
        this.requestPermissionListener = requestPermissionListener;
        if (ContextCompat.checkSelfPermission(this, permission)
                == PackageManager.PERMISSION_GRANTED) {
            requestPermissionListener.onRequestCallback(true);
        } else {
            ActivityCompat.requestPermissions(this, new String[]{permission},
                    PERMISSIONS_REQUEST_CODE);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           @NonNull String[] permissions,
                                           @NonNull int[] grantResults) {
        switch (requestCode) {
            case PERMISSIONS_REQUEST_CODE: {
                if (grantResults.length > 0 && grantResults[0] ==
                        PackageManager.PERMISSION_GRANTED) {
                    requestPermissionListener.onRequestCallback(true);
                } else {
                    requestPermissionListener.onRequestCallback(false);
                }
            }
        }
    }

    public interface RequestPermissionListener {
        void onRequestCallback(boolean granted);
    }
}
