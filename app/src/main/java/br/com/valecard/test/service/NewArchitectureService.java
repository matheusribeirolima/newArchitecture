package br.com.valecard.test.service;

import java.util.List;

import br.com.valecard.test.model.request.LoginRequest;
import br.com.valecard.test.model.response.CampaignResponse;
import br.com.valecard.test.model.response.LoginResponse;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface NewArchitectureService {

    @POST("services/establishmentAuth/login")
    Call<LoginResponse> login(@Body LoginRequest loginRequest);

    @GET("services/mobile/establishment/listAllCampaignsActiveEstablishment")
    Call<List<CampaignResponse>> getActiveCampaigns(@Query("conveniada_id") long establishmentId);
}
