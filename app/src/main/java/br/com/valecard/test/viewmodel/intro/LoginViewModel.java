package br.com.valecard.test.viewmodel.intro;

import android.arch.lifecycle.LiveData;
import android.databinding.BindingAdapter;
import android.databinding.Observable;
import android.databinding.ObservableBoolean;
import android.databinding.ObservableField;
import android.widget.EditText;

import com.orhanobut.hawk.Hawk;
import com.wei.android.lib.fingerprintidentify.FingerprintIdentify;

import br.com.valecard.test.R;
import br.com.valecard.test.TestApplication;
import br.com.valecard.test.helper.MaskHelper;
import br.com.valecard.test.helper.PreferencesHelper;
import br.com.valecard.test.helper.ValidatorHelper;
import br.com.valecard.test.model.request.LoginRequest;
import br.com.valecard.test.model.response.LoginResponse;
import br.com.valecard.test.repository.NewArchitectureRepository;
import br.com.valecard.test.service.BaseResponse;
import br.com.valecard.test.viewmodel.BaseViewModel;

public class LoginViewModel extends BaseViewModel<LoginViewModel.LoginListener> {

    public LoginRequest loginRequest = new LoginRequest();
    public ObservableField<String> cpfCnpjError = new ObservableField<>();
    public ObservableField<String> passwordError = new ObservableField<>();
    public ObservableBoolean loginFingerprint = new ObservableBoolean(Hawk.get(PreferencesHelper.FINGERPRINT_CHECKED, false));

    public LiveData<BaseResponse<LoginResponse>> login() {
        return NewArchitectureRepository.login(showLoading, loginRequest);
    }

    public void onSuccessLogin(LoginResponse loginResponse) {
        Hawk.put(PreferencesHelper.CURRENT_USER, loginResponse.getEstablishment());
        Hawk.put(PreferencesHelper.FINGERPRINT_LOGIN_USER, loginRequest.getLoginRequest().getLogin());
        Hawk.put(loginRequest.getLoginRequest().getLogin(), loginRequest.getLoginRequest().getPassword());
        Hawk.put(PreferencesHelper.FINGERPRINT_CHECKED, loginFingerprint.get());

        if (loginResponse.isTempPassActive()) {
            listener.onStartResetPassActivity();
        } else {
            listener.onStartMainActivity();
        }
        if (loginFingerprint.get()) {
            listener.onAlertFingerprint();
        }
    }

    public void onClickLogin() {
        if (loginRequest.getLogin() == null || loginRequest.getLogin().isEmpty()) {
            cpfCnpjError.set(TestApplication
                    .getInstance()
                    .getResources()
                    .getString(R.string.act_login_error_empty_login));
        } else if (!ValidatorHelper.isValidCPF(loginRequest.getLogin())) {
            cpfCnpjError.set(TestApplication
                    .getInstance()
                    .getResources()
                    .getString(R.string.act_login_error_invalid_cpf));
        } else if (!ValidatorHelper.isValidCNPJ(loginRequest.getLogin())) {
            cpfCnpjError.set(TestApplication
                    .getInstance()
                    .getResources()
                    .getString(R.string.act_login_error_invalid_cnpj));
        } else if (loginRequest.getPassword() == null || loginRequest.getPassword().isEmpty()) {
            passwordError.set(TestApplication
                    .getInstance()
                    .getResources()
                    .getString(R.string.act_login_error_empty_password));
        } else if (loginRequest.getPassword().length() < 5) {
            cpfCnpjError.set(TestApplication
                    .getInstance()
                    .getResources()
                    .getString(R.string.act_login_error_invalid_password));
        } else {
            listener.onClickLogin();
        }
    }

    public boolean showSwitch() {
        FingerprintIdentify fingerprintIdentify = new FingerprintIdentify(TestApplication.getInstance());
        return fingerprintIdentify.isHardwareEnable() &&
                fingerprintIdentify.isRegisteredFingerprint();
    }

    public void onClickForgot() {
        listener.onClickForgot();
    }

    public void onClickCreateAccount() {
        listener.onClickCreateAccount();
    }

    public void clearPasswordError(CharSequence s, int start, int before, int count) {
        passwordError.set("");
    }

    public void clearCpfCnpjError(CharSequence s, int start, int before, int count) {
        cpfCnpjError.set("");
    }

    @BindingAdapter("maskCpfCnpj")
    public static void setCpfCnpjMask(EditText view, String defaultMask) {
        view.addTextChangedListener(MaskHelper.insertCpfCnpjMask(view, defaultMask));
    }

    public interface LoginListener {

        void onClickLogin();

        void onClickForgot();

        void onClickCreateAccount();

        void onStartMainActivity();

        void onStartResetPassActivity();

        void onAlertFingerprint();
    }
}
