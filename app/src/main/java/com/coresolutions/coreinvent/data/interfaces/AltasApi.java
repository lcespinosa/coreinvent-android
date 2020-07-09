package com.coresolutions.coreinvent.data.interfaces;

import com.coresolutions.coreinvent.data.pojos.AssetPojo;
import com.coresolutions.coreinvent.data.pojos.FamilyPojo;
import com.coresolutions.coreinvent.data.pojos.FieldPojo;
import com.coresolutions.coreinvent.data.pojos.FindAssetPojo;
import com.coresolutions.coreinvent.data.pojos.Search;

import java.util.List;
import java.util.Map;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.PartMap;
import retrofit2.http.Path;

public interface AltasApi {

    @GET("nomenclature/families")
    Call<List<FamilyPojo>> getFamily(@Header("authorization") String token);

    @GET("operations/subscription/open/{subfamily}")
    Call<List<FieldPojo>> getFields(@Path("subfamily") int subfamilyid, @Header("authorization") String token);

    @Multipart
    @POST("operations/subscription/close")
    Call<AssetPojo> assetSubscription(@Header("authorization") String token, @PartMap() Map<String, RequestBody> partMap, @Part List<MultipartBody.Part> images);

    @POST("assets/search")
    Call<List<FindAssetPojo>> findAsset(@Header("authorization") String token, @Body Search search);

}
