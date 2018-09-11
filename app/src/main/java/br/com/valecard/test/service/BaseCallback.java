package br.com.valecard.test.service;

import android.support.annotation.NonNull;

import com.google.gson.Gson;

import java.util.Objects;

import br.com.valecard.test.R;
import br.com.valecard.test.TestApplication;
import br.com.valecard.test.model.response.ErrorResponse;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public abstract class BaseCallback<T> implements Callback<T> {

    public abstract void onSuccess(T response);

    public abstract void onError(ErrorResponse error);

    @Override
    public void onResponse(@NonNull Call<T> call, @NonNull Response<T> response) {
        if (response.isSuccessful()) {
            onSuccess(response.body());
        } else {
            try {
                onError(new Gson().fromJson(Objects.requireNonNull(response.errorBody()).string(),
                        ErrorResponse.class));
            } catch (Exception e) {
                ErrorResponse errorResponse = new ErrorResponse();
                errorResponse.setMessage(e.getMessage());
                onError(errorResponse);
            }
        }
    }

    @Override
    public void onFailure(@NonNull Call<T> call, @NonNull Throwable t) {
        t.printStackTrace();
        ErrorResponse response = new ErrorResponse();
        response.setMessage(TestApplication.getInstance().getResources().getString(R.string.error_message));
        onError(response);
    }
}
