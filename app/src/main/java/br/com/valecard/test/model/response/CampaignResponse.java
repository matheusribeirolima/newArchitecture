package br.com.valecard.test.model.response;

import com.google.gson.annotations.SerializedName;

public class CampaignResponse {

    @SerializedName("campanhaId")
    private long establishmentId;

    @SerializedName("nomeCampanha")
    private TypeCampaignResponse campaignName;

    @SerializedName("nomeEstabelecimento")
    private String establishmentName;

    @SerializedName("tempoExpiracao")
    private String expirationTime;

    @SerializedName("isPremium")
    private boolean premium;

    public long getEstablishmentId() {
        return establishmentId;
    }

    public void setEstablishmentId(long establishmentId) {
        this.establishmentId = establishmentId;
    }

    public TypeCampaignResponse getCampaignName() {
        return campaignName;
    }

    public void setCampaignName(TypeCampaignResponse campaignName) {
        this.campaignName = campaignName;
    }

    public String getEstablishmentName() {
        return establishmentName;
    }

    public void setEstablishmentName(String establishmentName) {
        this.establishmentName = establishmentName;
    }

    public String getExpirationTime() {
        return expirationTime;
    }

    public void setExpirationTime(String expirationTime) {
        this.expirationTime = expirationTime;
    }

    public boolean isPremium() {
        return premium;
    }

    public void setPremium(boolean premium) {
        this.premium = premium;
    }
}
