package br.com.valecard.test.viewmodel.intro;

import com.orhanobut.hawk.Hawk;
import com.synnapps.carouselview.ImageListener;

import br.com.valecard.test.R;
import br.com.valecard.test.helper.PreferencesHelper;
import br.com.valecard.test.viewmodel.BaseViewModel;

public class FirstAccessViewModel extends BaseViewModel<FirstAccessViewModel.FirstAccessListener> {

    private int[] images = {R.drawable.bg_walk_1, R.drawable.bg_walk_2, R.drawable.bg_walk_3};

    public int[] getImages() {
        return images;
    }

    public ImageListener changeImages() {
        return (position, imageView) -> imageView.setImageResource(images[position]);
    }

    public void onClickRegister() {
        Hawk.put(PreferencesHelper.FIRST_ACCESS, false);
        listener.onClickRegister();
    }

    public void onClickLogin() {
        Hawk.put(PreferencesHelper.FIRST_ACCESS, false);
        listener.onClickLogin();
    }

    public interface FirstAccessListener {

        void onClickRegister();

        void onClickLogin();
    }
}
