package br.com.valecard.test.view.intro;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.DecelerateInterpolator;
import android.widget.Toast;

import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;

import br.com.valecard.test.BR;
import br.com.valecard.test.R;
import br.com.valecard.test.databinding.ActivityFirstAccessBinding;
import br.com.valecard.test.databinding.ActivityLoginBinding;
import br.com.valecard.test.view.BaseActivity;
import br.com.valecard.test.viewmodel.intro.FirstAccessViewModel;

public class FirstAccessActivity extends BaseActivity<ActivityFirstAccessBinding, FirstAccessViewModel>
        implements FirstAccessViewModel.FirstAccessListener {

    ActivityFirstAccessBinding binding;
    FirstAccessViewModel viewModel;

    @Override
    public int getBindingVariable() {
        return BR.firstAccessViewModel;
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_first_access;
    }

    @Override
    public FirstAccessViewModel getViewModel() {
        viewModel = ViewModelProviders.of(this).get(FirstAccessViewModel.class);
        return viewModel;
    }

    @Override
    public void initBinding() {
        viewModel.setListener(this);
        binding = getDataBinding();

        binding.setFirstAccessViewModel(viewModel);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
                WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);

        configureCarousel();
    }

    @Override
    public void onClickRegister() {
        Toast.makeText(this, "Starts Register Activity", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onClickLogin() {
        startActivity(new Intent(this, LoginActivity.class));
    }

    private void configureCarousel() {
        binding.cvFirstAccess.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                if (position == 0) {
                    binding.llFirstAccess.setVisibility(View.VISIBLE);
                    binding.btLoginFirstAccess.setVisibility(View.VISIBLE);
                    YoYo.with(Techniques.FadeIn)
                            .duration(400)
                            .interpolate(new DecelerateInterpolator())
                            .playOn(binding.llFirstAccess);
                } else if (position == 2) {
                    binding.llFirstAccess.setVisibility(View.VISIBLE);
                    binding.btLoginFirstAccess.setVisibility(View.INVISIBLE);
                    YoYo.with(Techniques.FadeIn)
                            .duration(400)
                            .interpolate(new DecelerateInterpolator())
                            .playOn(binding.llFirstAccess);
                } else {
                    binding.llFirstAccess.setVisibility(View.GONE);
                }
            }

            @Override
            public void onPageSelected(int position) {

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }
}
