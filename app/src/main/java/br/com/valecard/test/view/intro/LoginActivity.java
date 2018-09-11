package br.com.valecard.test.view.intro;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.graphics.Paint;
import android.view.View;
import android.widget.Toast;

import com.wei.android.lib.fingerprintidentify.FingerprintIdentify;

import br.com.valecard.test.BR;
import br.com.valecard.test.R;
import br.com.valecard.test.databinding.ActivityLoginBinding;
import br.com.valecard.test.view.BaseActivity;
import br.com.valecard.test.view.main.MainActivity;
import br.com.valecard.test.viewmodel.intro.LoginViewModel;

public class LoginActivity extends BaseActivity<ActivityLoginBinding, LoginViewModel>
        implements LoginViewModel.LoginListener {

    ActivityLoginBinding binding;
    LoginViewModel viewModel;

    @Override
    public int getBindingVariable() {
        return BR.loginViewModel;
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_login;
    }

    @Override
    public LoginViewModel getViewModel() {
        viewModel = ViewModelProviders.of(this).get(LoginViewModel.class);
        return viewModel;
    }

    @Override
    public void initBinding() {
        viewModel.setListener(this);
        binding = getDataBinding();

        configureTextForgot();
        binding.tietCpfCnpjLogin.setText("15304417000115");
    }

    @Override
    public void onClickLogin() {
        viewModel.login().observe(this, loginResponse -> {
            if (loginResponse != null && loginResponse.getSuccessResponse() != null) {
                viewModel.onSuccessLogin(loginResponse.getSuccessResponse());
            } else if (loginResponse != null && loginResponse.getErrorResponse() != null) {
                Toast.makeText(getApplicationContext(),
                        loginResponse.getErrorResponse().getMessage(),
                        Toast.LENGTH_LONG).show();
            }
        });
    }

    @Override
    public void onClickForgot() {
        Toast.makeText(getApplicationContext(), "Starts Forgot Activity", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onClickCreateAccount() {
        Toast.makeText(getApplicationContext(), "Starts Create Activity", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onStartMainActivity() {
        Intent intent = new Intent(LoginActivity.this, MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
    }

    @Override
    public void onStartResetPassActivity() {
        Toast.makeText(this, "Starts ResetPass Activity", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onAlertFingerprint() {
        if (binding.swTouchIdLogin.isChecked()) {
            Toast.makeText(getApplicationContext(),
                    R.string.act_login_touch_id_active,
                    Toast.LENGTH_SHORT).show();
        }
    }

    private void configureTextForgot() {
        binding.tvForgotLogin.setPaintFlags(binding.tvForgotLogin.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);
    }
}
