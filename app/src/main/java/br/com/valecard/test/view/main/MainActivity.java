package br.com.valecard.test.view.main;

import android.arch.lifecycle.ViewModelProviders;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

import br.com.valecard.test.BR;
import br.com.valecard.test.R;
import br.com.valecard.test.databinding.ActivityMainBinding;
import br.com.valecard.test.view.BaseActivity;
import br.com.valecard.test.viewmodel.main.MainViewModel;

public class MainActivity extends BaseActivity<ActivityMainBinding, MainViewModel> implements MainViewModel.MainListener {

    ActivityMainBinding binding;
    MainViewModel viewModel;

    @Override
    public int getBindingVariable() {
        return BR.mainViewModel;
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    public MainViewModel getViewModel() {
        viewModel = ViewModelProviders.of(this).get(MainViewModel.class);
        return viewModel;
    }

    @Override
    public void initBinding() {
        viewModel.setListener(this);
        binding = getDataBinding();
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (savedInstanceState == null) {
            Toast.makeText(this, "Seleciona o primeiro", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.ic_campanhas:
                if (binding.bnvMain.getSelectedItemId() != R.id.ic_campanhas) {
                    Toast.makeText(this, "Campaigns", Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.ic_bonus:
                if (binding.bnvMain.getSelectedItemId() != R.id.ic_bonus) {
                    Toast.makeText(this, "Bonus", Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.ic_relatorios:
                if (binding.bnvMain.getSelectedItemId() != R.id.ic_relatorios) {
                    changeFragment(ListCampaignsFragment.newInstance());
                }
                break;
            case R.id.ic_perfil:
                if (binding.bnvMain.getSelectedItemId() != R.id.ic_perfil) {
                    Toast.makeText(this, "Profile", Toast.LENGTH_SHORT).show();
                }
                break;
            default:
                break;
        }
    }

    public void changeFragment(Fragment fragment) {
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.flMain, fragment)
                .commit();
    }
}
