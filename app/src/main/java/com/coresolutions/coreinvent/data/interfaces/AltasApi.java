package com.coresolutions.coreinvent.data.interfaces;

import com.coresolutions.coreinvent.ui.alta.FamilyPojo;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.GET;
import retrofit2.http.HEAD;
import retrofit2.http.Header;

public interface AltasApi {

    @GET("/nomenclature/families")
    Call<List<FamilyPojo>> getFamily(@Header("authorization") String token);

}
