package br.com.valecard.test.helper;

import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;

public class MaskHelper {

    private static final String maskCNPJ = "##.###.###/####-##";
    private static final String maskCPF = "###.###.###-##";

    public static String unMask(String s) {
        return s.replaceAll("[^0-9]*", "");
    }

    public static TextWatcher insertCpfCnpjMask(final EditText editText,
                                                final String defaultMask) {
        return new TextWatcher() {
            boolean isUpdating;
            String old = "";

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                String str = unMask(s.toString());
                String mask;
                switch (str.length()) {
                    case 11:
                        mask = maskCPF;
                        break;
                    case 14:
                        mask = maskCNPJ;
                        break;
                    default:
                        mask = defaultMask;
                        break;
                }

                String mascara = "";
                if (isUpdating) {
                    old = str;
                    isUpdating = false;
                    return;
                }
                int i = 0;
                for (char m : mask.toCharArray()) {
                    if ((m != '#' && str.length() > old.length()) ||
                            (m != '#' && str.length() < old.length() && str.length() != i)) {
                        mascara += m;
                        continue;
                    }

                    try {
                        mascara += str.charAt(i);
                    } catch (Exception e) {
                        break;
                    }
                    i++;
                }
                isUpdating = true;
                editText.setText(mascara);
                editText.setSelection(mascara.length());
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        };
    }
}

