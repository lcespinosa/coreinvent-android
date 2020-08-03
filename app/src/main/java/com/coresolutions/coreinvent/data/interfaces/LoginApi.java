package com.coresolutions.coreinvent.data.interfaces;

import com.coresolutions.coreinvent.data.model.LoggedInUser;
import com.coresolutions.coreinvent.data.pojos.User;

import java.util.HashMap;
import java.util.List;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.HEAD;
import retrofit2.http.Header;
import retrofit2.http.POST;

public interface LoginApi {

    @FormUrlEncoded
    @POST("auth/login")
    Call<String> loginUser(@Field("email") String email, @Field("password") String password);


    @HEAD("auth/logout")
    Call<Void> logoutUser(@Header("authorization") String token);

    @GET("auth/users")
    Call<List<User>> getAllUser(@Header("authorization") String token);


}
