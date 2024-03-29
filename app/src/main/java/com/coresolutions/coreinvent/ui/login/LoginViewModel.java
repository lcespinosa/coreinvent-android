package com.coresolutions.coreinvent.ui.login;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import android.content.SharedPreferences;
import android.util.Patterns;

import com.coresolutions.coreinvent.data.Constants;
import com.coresolutions.coreinvent.data.LoginRepository;
import com.coresolutions.coreinvent.data.RetrofitClient;
import com.coresolutions.coreinvent.data.interfaces.DashboardApi;
import com.coresolutions.coreinvent.data.interfaces.LoginApi;
import com.coresolutions.coreinvent.data.model.LoggedInUser;
import com.coresolutions.coreinvent.R;
import com.coresolutions.coreinvent.data.pojos.User;
import com.coresolutions.coreinvent.data.pojos.Year;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;

import java.util.HashMap;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

import static android.os.Debug.waitForDebugger;

public class LoginViewModel extends ViewModel {

    private MutableLiveData<LoginFormState> loginFormState = new MutableLiveData<>();
    private MutableLiveData<LoginResult> loginResult = new MutableLiveData<>();
    private MutableLiveData<List<User>> userResult = new MutableLiveData<>();
    private MutableLiveData<Integer> responseCodeResult = new MutableLiveData<>();
    private LoginRepository loginRepository;

    LoginViewModel(LoginRepository loginRepository) {
        this.loginRepository = loginRepository;
    }

    LiveData<LoginFormState> getLoginFormState() {
        return loginFormState;
    }

    LiveData<LoginResult> getLoginResult() {
        return loginResult;
    }

    public LiveData<Integer> getResponseCodeResult() {
        return responseCodeResult;
    }

    public LiveData<List<User>> getUserResult() {
        return userResult;
    }

    public void login(String username, String password, final SharedPreferences settings) {
        // can be launched in a separate asynchronous job
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Constants.API_URL)
                .addConverterFactory(ScalarsConverterFactory.create())
                .build();


        LoginApi loginApi = retrofit.create(LoginApi.class);
//        Call<String> loginUser = loginApi.loginUser("a@a.cu", "a");
        Call<String> loginUser = loginApi.loginUser(username, password);
        loginUser.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                if (response.isSuccessful()) {
//                    waitForDebugger();
                    String json = response.body();
                    Gson gson = new GsonBuilder().create();
                    JsonParser parser = new JsonParser();
                    try {
                        JsonElement jsonElement = parser.parse(json);
                        if (jsonElement.getAsJsonObject().has("message")) {
                            loginResult.setValue(new LoginResult(R.string.login_failed));
                        } else {
                            String token = jsonElement.getAsJsonObject().get("access_token").getAsString();
                            String expires_at = jsonElement.getAsJsonObject().get("expires_at").getAsString();
                            SharedPreferences.Editor editor = settings.edit();
                            editor.putString("access_token", "Bearer " + token);
                            editor.putString("expires_at", expires_at);
                            editor.commit();
                            LoggedInUser logged = gson.fromJson(jsonElement.getAsJsonObject().get("user").getAsJsonObject().get("data"), LoggedInUser.class);
                            loginResult.setValue(new LoginResult(new LoggedInUserView(logged.getName())));
                        }
                    } catch (Exception e) {
                        loginResult.setValue(new LoginResult(R.string.login_failed));
                    }
                } else {
                    loginResult.setValue(new LoginResult(R.string.login_failed));
                }
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
//                waitForDebugger();
                loginResult.setValue(new LoginResult(R.string.time_out));
            }
        });

//        if (result instanceof Result.Success) {
//            LoggedInUser data = ((Result.Success<LoggedInUser>) result).getData();
//            loginResult.setValue(new LoginResult(new LoggedInUserView(data.getName())));
//        } else {
//            loginResult.setValue(new LoginResult(R.string.login_failed));
//        }
    }

    public Call<Void> logout(String token) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Constants.API_URL)
//                .addConverterFactory(ScalarsConverterFactory.create())
                .build();
        LoginApi loginApi = retrofit.create(LoginApi.class);
        return loginApi.logoutUser("token " + token);
    }


    public void getAllUsers(String token) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Constants.API_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();


        LoginApi loginApi = retrofit.create(LoginApi.class);
        Call<List<User>> allUser = loginApi.getAllUser(token);
        allUser.enqueue(new Callback<List<User>>() {
            @Override
            public void onResponse(Call<List<User>> call, Response<List<User>> response) {
                if (response.isSuccessful()) {
                    List<User> yearList = response.body();
                    userResult.setValue(yearList);
                }
            }

            @Override
            public void onFailure(Call<List<User>> call, Throwable t) {
                userResult.setValue(null);
            }
        });
    }

    public void getUser(String token) {

        Call<HashMap<String, Object>> user = RetrofitClient.getInstance().getLoginApi().getUser(token);
        user.enqueue(new Callback<HashMap<String, Object>>() {
            @Override
            public void onResponse(Call<HashMap<String, Object>> call, Response<HashMap<String, Object>> response) {
                if (response.isSuccessful()) {
                    responseCodeResult.setValue(response.code());
                }
            }

            @Override
            public void onFailure(Call<HashMap<String, Object>> call, Throwable t) {
                responseCodeResult.setValue(401);
            }
        });
    }



    public void loginDataChanged(String username, String password) {
        if (!isUserNameValid(username)) {
            loginFormState.setValue(new LoginFormState(R.string.invalid_username, null));
        } else if (!isPasswordValid(password)) {
            loginFormState.setValue(new LoginFormState(null, R.string.invalid_password));
        } else {
            loginFormState.setValue(new LoginFormState(true));
        }
    }


    // A placeholder username validation check
    private boolean isUserNameValid(String username) {
        if (username == null) {
            return false;
        }
        if (username.contains("@")) {
            return Patterns.EMAIL_ADDRESS.matcher(username).matches();
        } else {
            return !username.trim().isEmpty();
        }
    }

    // A placeholder password validation check
    private boolean isPasswordValid(String password) {
        return password != null && password.trim().length() > 0;
    }
}
