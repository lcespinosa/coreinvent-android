package com.coresolutions.coreinvent.data.interfaces;

import com.coresolutions.coreinvent.data.pojos.AssetPojo;
import com.coresolutions.coreinvent.data.pojos.Center;
import com.coresolutions.coreinvent.data.pojos.FamilyPojo;
import com.coresolutions.coreinvent.data.pojos.FieldPojo;
import com.coresolutions.coreinvent.data.pojos.FindAssetPojo;
import com.coresolutions.coreinvent.data.pojos.Search;
import com.coresolutions.coreinvent.data.pojos.Unsubscription;
import com.coresolutions.coreinvent.data.pojos.UnsubscriptionRequestBody;
import com.coresolutions.coreinvent.ui.movement.MovementLocationFragment;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.PartMap;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface AltasApi {

    @GET("nomenclature/families")
    Call<List<FamilyPojo>> getFamily(@Header("authorization") String token);

    @GET("operations/subscription/open/{subfamily}")
    Call<List<FieldPojo>> getFields(@Path("subfamily") int subfamilyid, @Header("authorization") String token);


    //, @Part("notify_users") Integer[] notify_users

    @Multipart
    @POST("operations/subscription/close")
    Call<HashMap<String, String>> assetSubscription(@Header("authorization") String token, @PartMap() Map<String, RequestBody> partMap, @Part List<MultipartBody.Part> images);

    @POST("assets/search")
    Call<List<FindAssetPojo>> findAsset(@Header("authorization") String token, @Body Search search);

    @GET("assets/{asset}")
    Call<FindAssetPojo> getAssetsById(@Path("asset") int assetid, @Header("authorization") String token);

    @GET("operations/unsubscription/open")
    Call<Unsubscription> getUnsubscriptionData(@Header("authorization") String token);

    @POST("operations/unsubscription/close")
    Call<HashMap<String, String>> assetUnSubscription(@Header("authorization") String token, @Body UnsubscriptionRequestBody unsubscriptionRequestBody);

    @GET("assets/find/tag/{tag}")
    Call<FindAssetPojo> getAssetsByTag(@Header("authorization") String token, @Path("tag") String tag);

    @GET("nomenclature/centers")
    Call<List<Center>> getCenters(@Header("authorization") String token);

    @POST("operations/location/close")
    Call<HashMap<String, String>> assetMovement(@Header("authorization") String token, @Body MovementLocationFragment.MovementRequestBody movementRequestBody);

}
