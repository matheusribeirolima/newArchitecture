package br.com.valecard.test.viewmodel.main;

import android.arch.lifecycle.LiveData;

import java.util.List;

import br.com.valecard.test.model.response.CampaignResponse;
import br.com.valecard.test.repository.NewArchitectureRepository;
import br.com.valecard.test.service.BaseResponse;
import br.com.valecard.test.viewmodel.BaseViewModel;

public class ListCampaignsViewModel extends BaseViewModel<ListCampaignsViewModel.ListCampaignsListener> {

    public LiveData<BaseResponse<List<CampaignResponse>>> getActiveCampaigns() {
        return NewArchitectureRepository.getActiveCampaigns(showLoading);
    }

    public void onClickCreateCampaigns() {
        listener.onClickCreateCampaigns();
    }

    public interface ListCampaignsListener {
        void onClickCreateCampaigns();
    }
}
