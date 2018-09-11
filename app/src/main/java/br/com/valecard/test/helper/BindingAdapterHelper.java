package br.com.valecard.test.helper;

import android.databinding.BindingAdapter;
import android.support.design.widget.BottomNavigationView;

public class BindingAdapterHelper {

    @BindingAdapter("onNavigationItemSelected")
    public static void setOnNavigationItemSelectedListener(
            BottomNavigationView view,
            BottomNavigationView.OnNavigationItemSelectedListener listener) {
        view.setOnNavigationItemSelectedListener(listener);
    }
}
