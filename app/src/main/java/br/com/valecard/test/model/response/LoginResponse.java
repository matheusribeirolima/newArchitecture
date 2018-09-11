package br.com.valecard.test.model.response;

import com.google.gson.annotations.SerializedName;

public class LoginResponse {

    @SerializedName("token")
    private String token;

    @SerializedName("establishmentDto")
    private EstablishmentResponse establishment;

    @SerializedName("tempPasswdActive")
    private boolean tempPassActive;

    @SerializedName("firstAppLogin")
    private TypeFirstAppLogin firstLogin;

    @SerializedName("isDailyCampaignActive")
    private boolean dailyCampaignActive;

    @SerializedName("isPremium")
    private boolean premium;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public EstablishmentResponse getEstablishment() {
        return establishment;
    }

    public void setEstablishment(EstablishmentResponse establishment) {
        this.establishment = establishment;
    }

    public boolean isTempPassActive() {
        return tempPassActive;
    }

    public void setTempPassActive(boolean tempPassActive) {
        this.tempPassActive = tempPassActive;
    }

    public TypeFirstAppLogin getFirstLogin() {
        return firstLogin;
    }

    public void setFirstLogin(TypeFirstAppLogin firstLogin) {
        this.firstLogin = firstLogin;
    }

    public boolean isDailyCampaignActive() {
        return dailyCampaignActive;
    }

    public void setDailyCampaignActive(boolean dailyCampaignActive) {
        this.dailyCampaignActive = dailyCampaignActive;
    }

    public boolean isPremium() {
        return premium;
    }

    public void setPremium(boolean premium) {
        this.premium = premium;
    }
}
