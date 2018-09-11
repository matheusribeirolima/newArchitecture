package br.com.valecard.test.model.response;

import com.google.gson.annotations.SerializedName;

public class EstablishmentResponse {

    @SerializedName("id")
    private long id;

    @SerializedName("cpfCnpj")
    private String cpfCnpj;

    @SerializedName("cnae")
    private String cnae;

    @SerializedName("commercialPlacename")
    private String name;

    @SerializedName("percentualMinimo")
    private int minimumPercentage;

    @SerializedName("distance")
    private double distance;

    @SerializedName("favourite")
    private String favourite;

    @SerializedName("url")
    private String url;

    @SerializedName("endereco")
    private AddressResponse address;

    @SerializedName("telefone")
    private String phone;

    @SerializedName("email")
    private String email;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getCpfCnpj() {
        return cpfCnpj;
    }

    public void setCpfCnpj(String cpfCnpj) {
        this.cpfCnpj = cpfCnpj;
    }

    public String getCnae() {
        return cnae;
    }

    public void setCnae(String cnae) {
        this.cnae = cnae;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getMinimumPercentage() {
        return minimumPercentage;
    }

    public void setMinimumPercentage(int minimumPercentage) {
        this.minimumPercentage = minimumPercentage;
    }

    public double getDistance() {
        return distance;
    }

    public void setDistance(double distance) {
        this.distance = distance;
    }

    public String getFavourite() {
        return favourite;
    }

    public void setFavourite(String favourite) {
        this.favourite = favourite;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public AddressResponse getAddress() {
        return address;
    }

    public void setAddress(AddressResponse address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
