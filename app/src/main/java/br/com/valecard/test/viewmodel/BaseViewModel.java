package br.com.valecard.test.viewmodel;

import android.arch.lifecycle.ViewModel;
import android.databinding.Observable;
import android.databinding.ObservableBoolean;
import android.widget.Toast;

import br.com.valecard.test.TestApplication;

public abstract class BaseViewModel<T> extends ViewModel {

    protected ObservableBoolean showLoading = new ObservableBoolean(false);
    protected T listener;

    public void setListener(T listener) {
        this.listener = listener;
    }

    public ObservableBoolean getShowLoading() {
        return showLoading;
    }
}
