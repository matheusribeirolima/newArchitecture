package br.com.valecard.test.helper;

import android.text.TextUtils;
import android.util.Patterns;

public class ValidatorHelper {

    private static final int[] pesoCPF = {11, 10, 9, 8, 7, 6, 5, 4, 3, 2};
    private static final int[] pesoCNPJ = {6, 5, 4, 3, 2, 9, 8, 7, 6, 5, 4, 3, 2};

    private static int calcularDigito(String str, int[] peso) {
        int soma = 0;
        for (int indice = str.length() - 1, digito; indice >= 0; indice--) {
            digito = Integer.parseInt(str.substring(indice, indice + 1));
            soma += digito * peso[peso.length - str.length() + indice];
        }
        soma = 11 - soma % 11;
        return soma > 9 ? 0 : soma;
    }

    public static boolean isValidCPF(String cpf) {
        String cpfUnMask = MaskHelper.unMask(cpf);

        if ((cpfUnMask == null) || (cpfUnMask.length() < 11)) {
            return false;
        } else if (cpfUnMask.length() > 11) {
            return true;
        }

        Integer digito1 = calcularDigito(cpfUnMask.substring(0, 9), pesoCPF);
        Integer digito2 = calcularDigito(cpfUnMask.substring(0, 9) + digito1, pesoCPF);
        return cpfUnMask.equals(cpfUnMask.substring(0, 9) + digito1.toString() + digito2.toString());
    }

    public static boolean isValidCNPJ(String cnpj) {
        String cnpjUnMask = MaskHelper.unMask(cnpj);
        if ((cnpjUnMask == null) || (cnpjUnMask.length() > 11) && cnpjUnMask.length() < 14) {
            return false;
        } else if (cnpjUnMask.length() <= 11) {
            return true;
        }

        Integer digito1 = calcularDigito(cnpjUnMask.substring(0, 12), pesoCNPJ);
        Integer digito2 = calcularDigito(cnpjUnMask.substring(0, 12) + digito1, pesoCNPJ);
        return cnpjUnMask.equals(cnpjUnMask.substring(0, 12) + digito1.toString() + digito2.toString());
    }

    public static boolean isValidEmail(CharSequence target) {
        return !TextUtils.isEmpty(target) && Patterns.EMAIL_ADDRESS.matcher(target).matches();
    }

    public static boolean isValidPhone(CharSequence target) {
        return !TextUtils.isEmpty(target) &&
                (MaskHelper.unMask(target.toString()).length() == 10 ||
                        MaskHelper.unMask(target.toString()).length() == 11);
    }
}
