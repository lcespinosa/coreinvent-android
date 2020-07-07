package com.coresolutions.coreinvent.ui.alta;

import android.app.Application;
import android.graphics.Bitmap;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.coresolutions.coreinvent.data.Constants;
import com.coresolutions.coreinvent.data.LoginRepository;
import com.coresolutions.coreinvent.data.interfaces.AltasApi;
import com.coresolutions.coreinvent.data.pojos.AssetPojo;
import com.coresolutions.coreinvent.data.pojos.FamilyPojo;
import com.coresolutions.coreinvent.data.pojos.FieldPojo;
import com.coresolutions.coreinvent.data.pojos.FindAssetPojo;
import com.coresolutions.coreinvent.data.pojos.Search;
import com.google.gson.Gson;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static android.os.Debug.waitForDebugger;

public class AltaViewModel extends AndroidViewModel {

    private MutableLiveData<List<FamilyPojo>> familyResult = new MutableLiveData<>();
    private MutableLiveData<List<FieldPojo>> fieldResult = new MutableLiveData<>();
    private MutableLiveData<List<FindAssetPojo>> findResult = new MutableLiveData<>();
    private MutableLiveData<Integer> subscriptionResult = new MutableLiveData<>();

    public AltaViewModel(@NonNull Application application) {
        super(application);
    }


    LiveData<List<FamilyPojo>> getFamilyResult() {
        return familyResult;
    }

    LiveData<List<FieldPojo>> getRegisterResult() {
        return fieldResult;
    }

    public void getFamily(String token) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Constants.API_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();


        AltasApi altaApi = retrofit.create(AltasApi.class);
        Call<List<FamilyPojo>> family = altaApi.getFamily(token);
        family.enqueue(new Callback<List<FamilyPojo>>() {
            @Override
            public void onResponse(Call<List<FamilyPojo>> call, Response<List<FamilyPojo>> response) {
//                waitForDebugger();
                if (response.isSuccessful()) {
//                    waitForDebugger();
                    List<FamilyPojo> familyPojos = response.body();
                    familyResult.setValue(familyPojos);
                }
            }

            @Override
            public void onFailure(Call<List<FamilyPojo>> call, Throwable t) {
//                waitForDebugger();
                familyResult.setValue(null);
            }
        });

//        if (result instanceof Result.Success) {
//            LoggedInUser data = ((Result.Success<LoggedInUser>) result).getData();
//            loginResult.setValue(new LoginResult(new LoggedInUserView(data.getName())));
//        } else {
//            loginResult.setValue(new LoginResult(R.string.login_failed));
//        }
    }

    public MutableLiveData<List<FindAssetPojo>> getFindResult() {
        return findResult;
    }

    public void getFields(int subfamilyid, String token) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Constants.API_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        AltasApi altaApi = retrofit.create(AltasApi.class);
        Call<List<FieldPojo>> family = altaApi.getFields(subfamilyid, token);
        family.enqueue(new Callback<List<FieldPojo>>() {
            @Override
            public void onResponse(Call<List<FieldPojo>> call, Response<List<FieldPojo>> response) {
//                waitForDebugger();
                if (response.isSuccessful()) {
//                    waitForDebugger();
                    List<FieldPojo> fieldPojos = response.body();
                    fieldResult.setValue(fieldPojos);
                }
            }

            @Override
            public void onFailure(Call<List<FieldPojo>> call, Throwable t) {
//                waitForDebugger();
                familyResult.setValue(null);
            }
        });
    }


    public MutableLiveData<Integer> getSubscriptionResult() {
        return subscriptionResult;
    }

    public void assetSubscription(String token, AssetPojo assetPojo) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Constants.API_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        AltasApi altaApi = retrofit.create(AltasApi.class);
        Gson gson = new Gson();
        String json = gson.toJson(assetPojo);
        Call<AssetPojo> family = altaApi.assetSubscription(token, assetPojo);
        family.enqueue(new Callback<AssetPojo>() {
            @Override
            public void onResponse(Call<AssetPojo> call, Response<AssetPojo> response) {
//                waitForDebugger();
                subscriptionResult.setValue(response.code());
            }

            @Override
            public void onFailure(Call<AssetPojo> call, Throwable t) {
//                waitForDebugger();
                subscriptionResult.setValue(null);
            }

        });


    }


    public void findAsset(String token, Search search) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Constants.API_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        AltasApi altaApi = retrofit.create(AltasApi.class);
//        Gson gson = new Gson();
//        String json = gson.toJson(assetPojo);
        Call<List<FindAssetPojo>> family = altaApi.findAsset(token, search);
        family.enqueue(new Callback<List<FindAssetPojo>>() {
            @Override
            public void onResponse(Call<List<FindAssetPojo>> call, Response<List<FindAssetPojo>> response) {
//                waitForDebugger();
                List<FindAssetPojo> findAssetPojos = response.body();
                findResult.setValue(findAssetPojos);
            }

            @Override
            public void onFailure(Call<List<FindAssetPojo>> call, Throwable t) {
//                waitForDebugger();
                findResult.setValue(null);
            }

        });
    }


}

