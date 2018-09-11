package br.com.valecard.test.repository;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.databinding.ObservableBoolean;

import com.orhanobut.hawk.Hawk;

import java.util.List;

import br.com.valecard.test.TestApplication;
import br.com.valecard.test.helper.PreferencesHelper;
import br.com.valecard.test.model.request.LoginRequest;
import br.com.valecard.test.model.response.CampaignResponse;
import br.com.valecard.test.model.response.ErrorResponse;
import br.com.valecard.test.model.response.LoginResponse;
import br.com.valecard.test.service.BaseCallback;
import br.com.valecard.test.service.BaseResponse;
import br.com.valecard.test.service.NewArchitectureService;

public class NewArchitectureRepository {

    private static NewArchitectureService newArchitectureService = TestApplication.getNewArchitectureService();

    public static LiveData<BaseResponse<LoginResponse>> login(ObservableBoolean loading,
                                                              LoginRequest loginRequest) {
        loading.set(true);
        MutableLiveData<BaseResponse<LoginResponse>> data = new MutableLiveData<>();

        newArchitectureService.login(loginRequest.getLoginRequest()).enqueue(new BaseCallback<LoginResponse>() {
            @Override
            public void onSuccess(LoginResponse response) {
                Hawk.put(PreferencesHelper.ACCESS_TOKEN, response.getToken());
                Hawk.put(PreferencesHelper.DAILY_CAMPAIGN_FINISHED, response.isDailyCampaignActive());
                Hawk.put(PreferencesHelper.USER_PREMIUM, response.isPremium());
                data.setValue(new BaseResponse<>(response, null));
                loading.set(false);
            }

            @Override
            public void onError(ErrorResponse error) {
                data.setValue(new BaseResponse<>(null, error));
                loading.set(false);
            }
        });

        return data;
    }

    public static LiveData<BaseResponse<List<CampaignResponse>>> getActiveCampaigns(ObservableBoolean loading) {
        loading.set(true);
        MutableLiveData<BaseResponse<List<CampaignResponse>>> data = new MutableLiveData<>();

        newArchitectureService.getActiveCampaigns(PreferencesHelper.getCurrentEstablishment().getId())
                .enqueue(new BaseCallback<List<CampaignResponse>>() {
                    @Override
                    public void onSuccess(List<CampaignResponse> response) {
                        data.setValue(new BaseResponse<>(response, null));
                        loading.set(false);
                    }

                    @Override
                    public void onError(ErrorResponse error) {
                        data.setValue(new BaseResponse<>(null, error));
                        loading.set(false);
                    }
                });

        return data;
    }
}
