package br.com.valecard.test;

import android.app.Application;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonDeserializer;
import com.orhanobut.hawk.Hawk;

import java.util.Date;
import java.util.concurrent.TimeUnit;

import br.com.valecard.test.helper.PreferencesHelper;
import br.com.valecard.test.service.NewArchitectureService;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class TestApplication extends Application {

    private static TestApplication testApplication;
    private static NewArchitectureService newArchitectureService;

    public void onCreate() {
        super.onCreate();

        testApplication = this;

        Hawk.init(this).build();

        configureRetrofit();
    }

    public static synchronized TestApplication getInstance() {
        return testApplication;
    }

    public static synchronized NewArchitectureService getNewArchitectureService() {
        return newArchitectureService;
    }

    private void configureRetrofit() {
        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
        logging.setLevel(HttpLoggingInterceptor.Level.BODY);

        OkHttpClient client = new OkHttpClient.Builder()
                .readTimeout(10, TimeUnit.SECONDS)
                .connectTimeout(10, TimeUnit.SECONDS)
                .writeTimeout(10, TimeUnit.SECONDS)
                .addInterceptor(chain -> {
                    Request request = chain.request()
                            .newBuilder()
                            .addHeader("X-Auth-Token",
                                    Hawk.get(PreferencesHelper.ACCESS_TOKEN, ""))
                            .build();
                    return chain.proceed(request);
                })
                .addInterceptor(logging)
                .build();

        GsonBuilder builder = new GsonBuilder();
        builder.registerTypeAdapter(Date.class,
                (JsonDeserializer<Date>) (json, typeOfT, context) ->
                        new Date(json.getAsJsonPrimitive().getAsLong()));

        Gson gson = builder.create();

        newArchitectureService = new Retrofit.Builder()
                .baseUrl(getResources().getString(R.string.base_url))
                .client(client)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build()
                .create(NewArchitectureService.class);
    }
}
