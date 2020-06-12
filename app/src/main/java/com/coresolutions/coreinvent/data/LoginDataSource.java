package com.coresolutions.coreinvent.data;

import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.coresolutions.coreinvent.data.interfaces.LoginApi;
import com.coresolutions.coreinvent.data.model.LoggedInUser;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

import static android.os.Debug.waitForDebugger;

/**
 * Class that handles authentication w/ login credentials and retrieves user information.
 */
public class LoginDataSource {

    public Result<LoggedInUser> login(String username, String password) {

        try {
            // TODO: handle loggedInUser authentication
            LoggedInUser loggedInUser =
                    new LoggedInUser(
                            java.util.UUID.randomUUID().toString(),
                            "Jane Doe", "car@asd.cu");

            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl("https://coreinvent.herokuapp.com/api/")
                    .addConverterFactory(ScalarsConverterFactory.create())
                    .build();

            LoginApi loginApi = retrofit.create(LoginApi.class);
            Call<String> loginUser = loginApi.loginUser("liuver.carrera@coresolutions.es", "4dm1nC0r3*2020");
            loginUser.enqueue(new Callback<String>() {
                @Override
                public void onResponse(Call<String> call, Response<String> response) {
                    waitForDebugger();
                    String json = response.body();
                    Gson gson = new GsonBuilder().create();
                    JsonParser parser = new JsonParser();
                    JsonElement jsonElement = parser.parse(json);
                    LoggedInUser logged = gson.fromJson(jsonElement.getAsJsonObject().get("user").getAsJsonObject().get("data"), LoggedInUser.class);
                }

                @Override
                public void onFailure(Call<String> call, Throwable t) {
                    waitForDebugger();
                    String ms = t.getMessage();
                }
            });

            return new Result.Success<>(loggedInUser);
        } catch (Exception e) {
            return new Result.Error(new IOException("Error logging in", e));
        }
    }

    public void logout() {
        // TODO: revoke authentication
    }
}
