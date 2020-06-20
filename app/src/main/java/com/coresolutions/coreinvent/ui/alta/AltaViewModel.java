package com.coresolutions.coreinvent.ui.alta;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.coresolutions.coreinvent.data.Constants;
import com.coresolutions.coreinvent.data.LoginRepository;
import com.coresolutions.coreinvent.data.interfaces.AltasApi;
import com.coresolutions.coreinvent.ui.alta.pojos.AssetPojo;
import com.coresolutions.coreinvent.ui.alta.pojos.FamilyPojo;
import com.coresolutions.coreinvent.ui.alta.pojos.FieldListPojo;
import com.coresolutions.coreinvent.ui.alta.pojos.FieldPojo;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static android.os.Debug.waitForDebugger;

public class AltaViewModel extends AndroidViewModel {

    private MutableLiveData<List<FamilyPojo>> familyResult = new MutableLiveData<>();
    private MutableLiveData<FieldListPojo> fieldResult = new MutableLiveData<>();
    private MutableLiveData<Integer> subscriptionResult = new MutableLiveData<>();
    private LoginRepository loginRepository;

    public AltaViewModel(@NonNull Application application) {
        super(application);
    }


    LiveData<List<FamilyPojo>> getFamilyResult() {
        return familyResult;
    }

    LiveData<FieldListPojo> getRegisterResult() {
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

    public void getFields(int subfamilyid, String token) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Constants.API_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        AltasApi altaApi = retrofit.create(AltasApi.class);
        Call<FieldListPojo> family = altaApi.getFields(subfamilyid, token);
        family.enqueue(new Callback<FieldListPojo>() {
            @Override
            public void onResponse(Call<FieldListPojo> call, Response<FieldListPojo> response) {
//                waitForDebugger();
                if (response.isSuccessful()) {
//                    waitForDebugger();
                    FieldListPojo fieldPojos = response.body();
                    fieldResult.setValue(fieldPojos);
                }
            }

            @Override
            public void onFailure(Call<FieldListPojo> call, Throwable t) {
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
}
