package com.coresolutions.coreinvent.ui.alta;

import android.app.Application;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.FileUtils;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.coresolutions.coreinvent.data.Constants;
import com.coresolutions.coreinvent.data.LoginRepository;
import com.coresolutions.coreinvent.data.RetrofitClient;
import com.coresolutions.coreinvent.data.interfaces.AltasApi;
import com.coresolutions.coreinvent.data.interfaces.DashboardApi;
import com.coresolutions.coreinvent.data.pojos.AssetPojo;
import com.coresolutions.coreinvent.data.pojos.Center;
import com.coresolutions.coreinvent.data.pojos.FamilyPojo;
import com.coresolutions.coreinvent.data.pojos.FieldPojo;
import com.coresolutions.coreinvent.data.pojos.FindAssetPojo;
import com.coresolutions.coreinvent.data.pojos.Search;
import com.coresolutions.coreinvent.data.pojos.Unsubscription;
import com.coresolutions.coreinvent.data.pojos.UnsubscriptionRequestBody;
import com.coresolutions.coreinvent.ui.movement.MovementLocationFragment;
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


public class AltaViewModel extends AndroidViewModel {

    private MutableLiveData<List<FamilyPojo>> familyResult = new MutableLiveData<>();
    private MutableLiveData<List<FieldPojo>> fieldResult = new MutableLiveData<>();
    private MutableLiveData<List<FindAssetPojo>> findResult = new MutableLiveData<>();
    private MutableLiveData<HashMap<String, String>> subscriptionResult = new MutableLiveData<>();
    private MutableLiveData<FindAssetPojo> assetResult = new MutableLiveData<>();
    private MutableLiveData<Unsubscription> unsubscriptionResult = new MutableLiveData<>();
    private MutableLiveData<HashMap<String, String>> responseBodyUnsubscription = new MutableLiveData<>();
    private MutableLiveData<List<Center>> centerResult = new MutableLiveData<>();

    public AltaViewModel(@NonNull Application application) {
        super(application);
    }


    LiveData<List<FamilyPojo>> getFamilyResult() {
        return familyResult;
    }

    public LiveData<Unsubscription> getUnsubscriptionResult() {
        return unsubscriptionResult;
    }

    public MutableLiveData<HashMap<String, String>> getResponseBodyUnsubscription() {
        return responseBodyUnsubscription;
    }

    public LiveData<List<Center>> getCenterResult() {
        return centerResult;
    }

    public LiveData<List<FieldPojo>> getRegisterResult() {
        return fieldResult;
    }


    public MutableLiveData<FindAssetPojo> getAssetResult() {
        return assetResult;
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


    public MutableLiveData<HashMap<String, String>> getSubscriptionResult() {
        return subscriptionResult;
    }

    public void assetSubscription(String token, AssetPojo assetPojo) {

        AltasApi altaApi = RetrofitClient.getInstance().getAltasApi();
        Gson gson = new Gson();
        String json = gson.toJson(assetPojo);

        Map<String, String> map = gson.fromJson(json, Map.class);
        map.remove("image");
        map.remove("notify_users");
        HashMap<String, RequestBody> maps = new HashMap<>();

        for (Map.Entry<String, String> entry : map.entrySet()) {
            maps.put(entry.getKey(), RequestBody.create(MediaType.parse("multipart/form-data"), entry.getValue()));
        }
//        maps.put("notify_users", RequestBody.create(MediaType.parse("multipart/form-data"), gson.toJson(assetPojo.getNotifyUsers())));
        List<MultipartBody.Part> parts = new ArrayList<>();
        if (assetPojo.getImage() != null) {
            // Create a request body with file and image media type
            RequestBody fileReqBody = RequestBody.create(MediaType.parse("image/*"), assetPojo.getImage());
            // Create MultipartBody.Part using file request-body,file name and part name
            MultipartBody.Part part = MultipartBody.Part.createFormData("images[]", assetPojo.getImage().getName(), fileReqBody);
//            MultipartBody.Part part1 = MultipartBody.Part.createFormData("notify_users", gson.toJson(assetPojo.getNotifyUsers()));

            parts.add(part);
//            parts.add(part1);
        }
        Integer[] myArray = new Integer[assetPojo.getNotifyUsers().size()];
        //, assetPojo.getNotifyUsers().toArray(myArray)
        List<Integer> notify_users = assetPojo.getNotifyUsers();
        List<String> items = new ArrayList();
        items.add("1");
        Call<HashMap<String, String>> family = altaApi.assetSubscription(token, maps, parts, notify_users);
        family.enqueue(new Callback<HashMap<String, String>>() {
            @Override
            public void onResponse(Call<HashMap<String, String>> call, Response<HashMap<String, String>> response) {
//                waitForDebugger();
                HashMap<String, String> hashMap = response.body();
                subscriptionResult.setValue(hashMap);
            }

            @Override
            public void onFailure(Call<HashMap<String, String>> call, Throwable t) {
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


    public void getAssetById(int assetid, String token) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Constants.API_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        AltasApi altasApi = retrofit.create(AltasApi.class);
        Call<FindAssetPojo> asset = altasApi.getAssetsById(assetid, token);
        asset.enqueue(new Callback<FindAssetPojo>() {
            @Override
            public void onResponse(Call<FindAssetPojo> call, Response<FindAssetPojo> response) {
                if (response.isSuccessful()) {
                    FindAssetPojo asset = response.body();
                    assetResult.setValue(asset);
                }
            }

            @Override
            public void onFailure(Call<FindAssetPojo> call, Throwable t) {
                assetResult.setValue(null);
            }
        });
    }

    public void getAssetByTag(String token, String tag) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Constants.API_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        AltasApi altasApi = retrofit.create(AltasApi.class);
        Call<FindAssetPojo> asset = altasApi.getAssetsByTag(token, tag);
        asset.enqueue(new Callback<FindAssetPojo>() {
            @Override
            public void onResponse(Call<FindAssetPojo> call, Response<FindAssetPojo> response) {
                if (response.isSuccessful()) {
                    FindAssetPojo asset = response.body();
                    if (asset.getId() != null) {
                        assetResult.setValue(asset);
                    } else {
                        assetResult.setValue(null);
                    }
                }
            }

            @Override
            public void onFailure(Call<FindAssetPojo> call, Throwable t) {
                assetResult.setValue(null);
            }
        });
    }


    public void getUnsubscriptionData(String token) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Constants.API_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();


        AltasApi altaApi = retrofit.create(AltasApi.class);
        Call<Unsubscription> family = altaApi.getUnsubscriptionData(token);
        family.enqueue(new Callback<Unsubscription>() {
            @Override
            public void onResponse(Call<Unsubscription> call, Response<Unsubscription> response) {
                if (response.isSuccessful()) {
                    Unsubscription unsubscriptionData = response.body();
                    unsubscriptionResult.setValue(unsubscriptionData);
                }
            }

            @Override
            public void onFailure(Call<Unsubscription> call, Throwable t) {
                unsubscriptionResult.setValue(null);
            }
        });

    }

    public void assetUnSubscription(String token, UnsubscriptionRequestBody unsubscriptionRequestBody) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Constants.API_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();


        Gson gson = new Gson();
        String json = gson.toJson(unsubscriptionRequestBody);

        AltasApi altaApi = retrofit.create(AltasApi.class);
        Call<HashMap<String, String>> family = altaApi.assetUnSubscription(token, unsubscriptionRequestBody);
        family.enqueue(new Callback<HashMap<String, String>>() {
            @Override
            public void onResponse(Call<HashMap<String, String>> call, Response<HashMap<String, String>> response) {
                if (response.isSuccessful()) {
                    HashMap<String, String> responseBody = response.body();
                    responseBodyUnsubscription.setValue(responseBody);
                }
            }

            @Override
            public void onFailure(Call<HashMap<String, String>> call, Throwable t) {
                responseBodyUnsubscription.setValue(null);
            }
        });

    }


    public void getCenters(String token) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Constants.API_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        AltasApi altasApi = retrofit.create(AltasApi.class);
        Call<List<Center>> centers = altasApi.getCenters(token);
        centers.enqueue(new Callback<List<Center>>() {
            @Override
            public void onResponse(Call<List<Center>> call, Response<List<Center>> response) {
                if (response.isSuccessful()) {
                    List<Center> centerList = response.body();
                    centerResult.setValue(centerList);
                }
            }

            @Override
            public void onFailure(Call<List<Center>> call, Throwable t) {
                centerResult.setValue(null);
            }
        });
    }

    public void assetMovement(String token, MovementLocationFragment.MovementRequestBody movementRequestBody) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Constants.API_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();


//        Gson gson = new Gson();
//        String json = gson.toJson(unsubscriptionRequestBody);

        AltasApi altaApi = retrofit.create(AltasApi.class);
        Call<HashMap<String, String>> movementResponse = altaApi.assetMovement(token, movementRequestBody);
        movementResponse.enqueue(new Callback<HashMap<String, String>>() {
            @Override
            public void onResponse(Call<HashMap<String, String>> call, Response<HashMap<String, String>> response) {
                if (response.isSuccessful()) {
                    HashMap<String, String> responseBody = response.body();
                    responseBodyUnsubscription.setValue(responseBody);
                }
            }

            @Override
            public void onFailure(Call<HashMap<String, String>> call, Throwable t) {
                responseBodyUnsubscription.setValue(null);
            }
        });

    }


}

