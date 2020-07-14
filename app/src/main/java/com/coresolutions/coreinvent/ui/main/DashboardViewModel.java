package com.coresolutions.coreinvent.ui.main;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.coresolutions.coreinvent.data.Constants;
import com.coresolutions.coreinvent.data.interfaces.AltasApi;
import com.coresolutions.coreinvent.data.interfaces.DashboardApi;
import com.coresolutions.coreinvent.data.pojos.AssetPojo;
import com.coresolutions.coreinvent.data.pojos.FamilyPojo;
import com.coresolutions.coreinvent.data.pojos.FieldPojo;
import com.coresolutions.coreinvent.data.pojos.FindAssetPojo;
import com.coresolutions.coreinvent.data.pojos.Search;
import com.coresolutions.coreinvent.data.pojos.Year;
import com.google.gson.Gson;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class DashboardViewModel extends AndroidViewModel {

    private MutableLiveData<List<Year>> yearsResult = new MutableLiveData<>();

    public DashboardViewModel(@NonNull Application application) {
        super(application);
    }

    public MutableLiveData<List<Year>> getYearsResult() {
        return yearsResult;
    }

    public void getYears(String token) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Constants.API_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();


        DashboardApi dashboardApi = retrofit.create(DashboardApi.class);
        Call<List<Year>> years = dashboardApi.getYears(token);
        years.enqueue(new Callback<List<Year>>() {
            @Override
            public void onResponse(Call<List<Year>> call, Response<List<Year>> response) {
                if (response.isSuccessful()) {
                    List<Year> yearList = response.body();
                    yearsResult.setValue(yearList);
                }
            }

            @Override
            public void onFailure(Call<List<Year>> call, Throwable t) {
                yearsResult.setValue(null);
            }
        });
    }
}

