package br.com.valecard.test.helper;

import com.orhanobut.hawk.Hawk;

import br.com.valecard.test.model.response.EstablishmentResponse;

public class PreferencesHelper {

    public static final String CURRENT_USER = "sp_current_user";
    public static final String FIRST_ACCESS = "sp_first_access";
    public static final String FINGERPRINT_CHECKED = "sp_fingerprint_checked";
    public static final String FINGERPRINT_LOGIN_USER = "sp_fingerprint_login_user";
    public static final String CURRENT_PROSPECT = "sp_current_prospect";
    public static final String CPF_CNPJ_PROSPECT = "sp_cpf_cnpj_prospect";
    public static final String NAME_REGISTER = "sp_name_register";
    public static final String CASH_BACK_REGISTER = "sp_cash_back_register";
    public static final String CPF_CNPJ_FORGOT = "sp_cpf_cnpj_forgot";
    public static final String ACCESS_TOKEN = "sp_access_token";
    public static final String DAILY_CAMPAIGN_FINISHED = "sp_daily_campaign_finished";
    public static final String USER_PREMIUM = "sp_user_premium";

    public static void logout() {
        boolean firstAccess = Hawk.get(FIRST_ACCESS, false);
        boolean fingerprint = Hawk.get(FINGERPRINT_CHECKED, false);
        String login = Hawk.get(FINGERPRINT_LOGIN_USER, "");

        Hawk.deleteAll();
        Hawk.put(FIRST_ACCESS, firstAccess);
        Hawk.put(FINGERPRINT_CHECKED, fingerprint);
        Hawk.put(FINGERPRINT_LOGIN_USER, login);
    }

    public static EstablishmentResponse getCurrentEstablishment() {
        return Hawk.get(PreferencesHelper.CURRENT_USER);
    }
}
