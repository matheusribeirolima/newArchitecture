package br.com.valecard.test.view.intro;

import android.animation.Animator;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.view.animation.AnticipateOvershootInterpolator;
import android.widget.Toast;

import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;

import br.com.valecard.test.BR;
import br.com.valecard.test.R;
import br.com.valecard.test.databinding.ActivitySplashBinding;
import br.com.valecard.test.view.BaseActivity;
import br.com.valecard.test.view.main.MainActivity;
import br.com.valecard.test.viewmodel.intro.SplashViewModel;

public class SplashActivity extends BaseActivity<ActivitySplashBinding, SplashViewModel> implements SplashViewModel.SplashListener {

    ActivitySplashBinding binding;
    SplashViewModel viewModel;

    @Override
    public int getBindingVariable() {
        return BR.splashViewModel;
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_splash;
    }

    @Override
    public SplashViewModel getViewModel() {
        viewModel = ViewModelProviders.of(this).get(SplashViewModel.class);
        return viewModel;
    }

    @Override
    public void initBinding() {
        viewModel.setListener(this);
        binding = getDataBinding();

        startAnimation();
    }

    private void startAnimation() {
        YoYo.with(Techniques.ZoomInRight)
                .duration(800)
                .interpolate(new AnticipateOvershootInterpolator())
                .withListener(new Animator.AnimatorListener() {
                    @Override
                    public void onAnimationStart(Animator animation) {

                    }

                    @Override
                    public void onAnimationEnd(Animator animation) {
                        viewModel.decideNextActivity();
                    }

                    @Override
                    public void onAnimationCancel(Animator animation) {

                    }

                    @Override
                    public void onAnimationRepeat(Animator animation) {

                    }
                })
                .playOn(binding.ivLogoSplash);
    }

    @Override
    public void openFirstAccessActivity() {
        Intent intent = new Intent(SplashActivity.this, FirstAccessActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
    }

    @Override
    public void openLoginFingerprintActivity() {
        Toast.makeText(getApplicationContext(), "Fingerprint", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void openMainActivity() {
        Intent intent = new Intent(SplashActivity.this, MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
    }

    @Override
    public void openLoginActivity() {
        Intent intent = new Intent(SplashActivity.this, LoginActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK |
                Intent.FLAG_ACTIVITY_CLEAR_TASK |
                Intent.FLAG_ACTIVITY_NO_ANIMATION);
        startActivity(intent);
        overridePendingTransition(0, 0);
    }
}
