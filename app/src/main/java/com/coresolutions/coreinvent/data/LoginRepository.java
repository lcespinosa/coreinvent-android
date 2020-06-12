package com.coresolutions.coreinvent.data;

import com.coresolutions.coreinvent.data.interfaces.LoginApi;
import com.coresolutions.coreinvent.data.model.LoggedInUser;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.scalars.ScalarsConverterFactory;

import static android.os.Debug.waitForDebugger;

/**
 * Class that requests authentication and user information from the remote data source and
 * maintains an in-memory cache of login status and user credentials information.
 */
public class LoginRepository {

    private static volatile LoginRepository instance;

    private LoginDataSource dataSource;

    // If user credentials will be cached in local storage, it is recommended it be encrypted
    // @see https://developer.android.com/training/articles/keystore
    private LoggedInUser user = null;

    // private constructor : singleton access
    private LoginRepository(LoginDataSource dataSource) {
        this.dataSource = dataSource;
    }

    public static LoginRepository getInstance(LoginDataSource dataSource) {
        if (instance == null) {
            instance = new LoginRepository(dataSource);
        }
        return instance;
    }

    public boolean isLoggedIn() {
        return user != null;
    }

    public void logout() {
        user = null;
        dataSource.logout();
    }

    private void setLoggedInUser(LoggedInUser user) {
        this.user = user;
        // If user credentials will be cached in local storage, it is recommended it be encrypted
        // @see https://developer.android.com/training/articles/keystore
    }

    public Result<LoggedInUser> login(String username, String password) {
        // handle login
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://coreinvent.herokuapp.com/api/")
                .addConverterFactory(ScalarsConverterFactory.create())
                .build();

        LoginApi loginApi = retrofit.create(LoginApi.class);
        Call<String> loginUser = loginApi.loginUser(username, password);
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


        Result<LoggedInUser> result = dataSource.login(username, password);
        if (result instanceof Result.Success) {
            setLoggedInUser(((Result.Success<LoggedInUser>) result).getData());
        }
        return result;
    }
}
