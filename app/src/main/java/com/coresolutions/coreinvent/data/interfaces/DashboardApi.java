package com.coresolutions.coreinvent.data.interfaces;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.HEAD;
import retrofit2.http.Header;
import retrofit2.http.POST;

public interface DashboardApi {

    @GET("auth/dashboard/asset_subscription")
    Call<String> assetSubscription(@Field("email") String email, @Field("password") String pawssword);


    @HEAD("auth/logout")
    Call<Void> logoutUser(@Header("authorization") String token);


}
