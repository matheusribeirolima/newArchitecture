package br.com.valecard.test.viewmodel.main;

import android.support.design.widget.BottomNavigationView;
import android.view.MenuItem;

import br.com.valecard.test.viewmodel.BaseViewModel;

public class MainViewModel extends BaseViewModel<MainViewModel.MainListener> {

    public BottomNavigationView.OnNavigationItemSelectedListener getBottomListener () {
        return item -> {
            listener.onItemSelected(item);
            return true;
        };
    }

    public interface MainListener {

        void onItemSelected(MenuItem item);
    }
}
