package br.com.valecard.test.model.request;

import android.databinding.BaseObservable;
import android.databinding.Bindable;

import com.google.gson.annotations.SerializedName;

import org.apache.commons.codec.binary.Hex;
import org.apache.commons.codec.digest.DigestUtils;

import br.com.valecard.test.BR;
import br.com.valecard.test.helper.MaskHelper;

public class LoginRequest extends BaseObservable {

    @SerializedName("login")
    private String login;

    @SerializedName("password")
    private String password;

    @Bindable
    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
        notifyPropertyChanged(BR.login);
    }

    @Bindable
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
        notifyPropertyChanged(BR.password);
    }

    public LoginRequest getLoginRequest() {
        LoginRequest loginRequest = new LoginRequest();

        loginRequest.setLogin(MaskHelper.unMask(login));
        loginRequest.setPassword(new String(Hex.encodeHex(DigestUtils.sha256(password))));

        return loginRequest;
    }
}
