package br.com.valecard.test.view;

import android.app.Dialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.Objects;

import br.com.valecard.test.R;

public class LoadingDialog extends DialogFragment {

    private static final String TAG = "tag_loading";
    private static LoadingDialog biisLoadingDialogFragment;
    private static boolean show = false;

    public static synchronized LoadingDialog getInstance() {
        if (biisLoadingDialogFragment == null) {
            biisLoadingDialogFragment = new LoadingDialog();
        }
        return biisLoadingDialogFragment;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        setCancelable(false);
        return new AlertDialog.Builder(Objects.requireNonNull(getActivity()))
                .setView(getActivity()
                        .getLayoutInflater()
                        .inflate(R.layout.dialog_loading, null))
                .create();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        Objects.requireNonNull(getDialog().getWindow())
                .setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    public void show(FragmentManager manager) {
        if (show || (getDialog() != null && getDialog().isShowing())) return;

        try {
            show = true;
            super.show(manager, TAG);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void dismiss() {
        try {
            show = false;
            super.dismiss();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
