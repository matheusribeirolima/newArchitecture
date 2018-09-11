package br.com.valecard.test.model.response;

import com.google.gson.annotations.SerializedName;

public class AddressResponse {

    @SerializedName("logradouro")
    private String address;

    @SerializedName("localidade")
    private String locality;

    @SerializedName("complemento")
    private String complement;

    @SerializedName("bairro")
    private String neighborhood;

    @SerializedName("cep")
    private String cep;

    @SerializedName("estado")
    private String state;

    @SerializedName("numero")
    private String number;

    @SerializedName("tipoLogradouro")
    private String type;

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getLocality() {
        return locality;
    }

    public void setLocality(String locality) {
        this.locality = locality;
    }

    public String getComplement() {
        return complement;
    }

    public void setComplement(String complement) {
        this.complement = complement;
    }

    public String getNeighborhood() {
        return neighborhood;
    }

    public void setNeighborhood(String neighborhood) {
        this.neighborhood = neighborhood;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
