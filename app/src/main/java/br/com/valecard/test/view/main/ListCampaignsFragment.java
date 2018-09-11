package br.com.valecard.test.view.main;

import android.arch.lifecycle.ViewModelProviders;
import android.graphics.Typeface;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.Toolbar;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.StyleSpan;
import android.widget.Toast;

import br.com.valecard.test.BR;
import br.com.valecard.test.R;
import br.com.valecard.test.databinding.FragmentListCampaignsBinding;
import br.com.valecard.test.view.BaseFragment;
import br.com.valecard.test.viewmodel.main.ListCampaignsViewModel;

public class ListCampaignsFragment extends BaseFragment<FragmentListCampaignsBinding, ListCampaignsViewModel>
        implements ListCampaignsViewModel.ListCampaignsListener {

    FragmentListCampaignsBinding binding;
    ListCampaignsViewModel viewModel;
    ListCampaignsAdapter listCampaignsAdapter;

    public static ListCampaignsFragment newInstance() {
        return new ListCampaignsFragment();
    }

    @Override
    public int getBindingVariable() {
        return BR.listCampaignsViewModel;
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_list_campaigns;
    }

    @Override
    public ListCampaignsViewModel getViewModel() {
        viewModel = ViewModelProviders.of(this).get(ListCampaignsViewModel.class);
        return viewModel;
    }

    @Override
    public void initBinding() {
        viewModel.setListener(this);
        binding = getDataBinding();

        configureToolbar();
        configureRecycler();
        subscribeCampaigns();
    }

    @Override
    public void onClickCreateCampaigns() {
        Toast.makeText(getBaseActivity(), "Create Campaigns", Toast.LENGTH_SHORT).show();
    }

    private void configureToolbar() {
        getBaseActivity().setSupportActionBar((Toolbar) binding.toolbarCampaigns);
        if (getBaseActivity().getSupportActionBar() != null) {
            getBaseActivity().getSupportActionBar().setDisplayHomeAsUpEnabled(false);
        }
    }

    private void configureRecycler() {
        listCampaignsAdapter = new ListCampaignsAdapter();
        binding.rvCampaigns.setLayoutManager(new LinearLayoutManager(getContext(),
                LinearLayoutManager.VERTICAL,
                false));
        binding.rvCampaigns.setHasFixedSize(false);
        binding.rvCampaigns.setAdapter(listCampaignsAdapter);
    }

    private void configureSubtitle(int size) {
        String first = "Ativas: ";
        String second = " de 7 campanhas";

        SpannableString str = new SpannableString(first + size + second);
        str.setSpan(new StyleSpan(Typeface.BOLD),
                0,
                first.length(),
                Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        binding.tvCampaigns.setText(str);
    }

    private void subscribeCampaigns() {
        viewModel.getActiveCampaigns().observe(this, campaignsResponse -> {
            if (campaignsResponse != null && campaignsResponse.getSuccessResponse() != null) {
                configureSubtitle(campaignsResponse.getSuccessResponse().size());
                listCampaignsAdapter.setItems(campaignsResponse.getSuccessResponse());
            } else if (campaignsResponse != null && campaignsResponse.getErrorResponse() != null) {
                Toast.makeText(getBaseActivity(),
                        campaignsResponse.getErrorResponse().getMessage(),
                        Toast.LENGTH_SHORT).show();
            }
        });
    }
}
