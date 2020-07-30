package com.coresolutions.coreinvent.data.interfaces;

import com.coresolutions.coreinvent.data.pojos.Dashboard;
import com.coresolutions.coreinvent.data.pojos.FamilyPojo;
import com.coresolutions.coreinvent.data.pojos.FieldPojo;
import com.coresolutions.coreinvent.data.pojos.FindAssetPojo;
import com.coresolutions.coreinvent.data.pojos.Year;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.HEAD;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface DashboardApi {

    @GET("dashboard/years")
    Call<List<Year>> getYears(@Header("authorization") String token);

    @GET("dashboard/info")
    Call<Dashboard> getDashboardInfo(@Header("authorization") String token, @Query("year") int year);


}
