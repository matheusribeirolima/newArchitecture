package br.com.valecard.test.viewmodel.intro;

import com.orhanobut.hawk.Hawk;

import br.com.valecard.test.helper.PreferencesHelper;
import br.com.valecard.test.viewmodel.BaseViewModel;

public class SplashViewModel extends BaseViewModel<SplashViewModel.SplashListener> {

    public void decideNextActivity() {
        if (Hawk.get(PreferencesHelper.FIRST_ACCESS, true)) {
            listener.openFirstAccessActivity();
        } else if (PreferencesHelper.getCurrentEstablishment() != null) {
            listener.openMainActivity();
        } else {
            if (!Hawk.get(PreferencesHelper.FINGERPRINT_CHECKED, false)) {
                listener.openLoginActivity();
            } else {
                listener.openLoginFingerprintActivity();
            }
        }
    }

    public interface SplashListener {
        void openFirstAccessActivity();

        void openLoginFingerprintActivity();

        void openMainActivity();

        void openLoginActivity();
    }
}
