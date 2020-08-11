package com.coresolutions.coreinvent.data;

import com.coresolutions.coreinvent.data.interfaces.AltasApi;
import com.coresolutions.coreinvent.data.interfaces.DashboardApi;
import com.coresolutions.coreinvent.data.interfaces.LoginApi;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClient {

    private static RetrofitClient mInstance;
    private Retrofit retrofit;


    private RetrofitClient() {
        retrofit = new Retrofit.Builder()
                .baseUrl(Constants.API_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    public static synchronized RetrofitClient getInstance() {
        if (mInstance == null) {
            mInstance = new RetrofitClient();
        }
        return mInstance;
    }

    public AltasApi getAltasApi() {
        return retrofit.create(AltasApi.class);
    }

    public DashboardApi getDashboardApi() {
        return retrofit.create(DashboardApi.class);
    }

    public LoginApi getLoginApi() {
        return retrofit.create(LoginApi.class);
    }
}