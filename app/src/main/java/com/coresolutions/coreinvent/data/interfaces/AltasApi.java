package com.coresolutions.coreinvent.data.interfaces;

import com.coresolutions.coreinvent.ui.alta.pojos.FamilyPojo;
import com.coresolutions.coreinvent.ui.alta.pojos.FieldPojo;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Path;

public interface AltasApi {

    @GET("nomenclature/families")
    Call<List<FamilyPojo>> getFamily(@Header("authorization") String token);

    @GET("operations/subscription/open/{subfamily}")
    Call<List<FieldPojo>> getFields(@Path("subfamily") int subfamilyid, @Header("authorization") String token);





}
