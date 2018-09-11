package br.com.valecard.test.model.response;

import com.google.gson.annotations.SerializedName;

public enum TypeCampaignResponse {

    @SerializedName("DIARIA")
    DAILY("Diária"),

    @SerializedName("ANIVERSARIO")
    BIRTHDAY("Aniversário"),

    @SerializedName("CRONOS")
    CHRONOS("Chronos"),

    @SerializedName("PROGRESSIVA")
    PROGRESSIVE("Progressiva"),

    @SerializedName("VOLTE_SEMPRE")
    ALWAYS_BACK("Volte Sempre"),

    @SerializedName("SEGMENTADA")
    SEGMENTED("Segmentada"),

    @SerializedName("COLETIVA")
    COLETIVE("Coletiva"),

    DEFAULT("Default");

    private String id;

    TypeCampaignResponse(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }
}
