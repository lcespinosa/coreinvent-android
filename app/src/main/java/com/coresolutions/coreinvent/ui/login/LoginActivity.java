package com.coresolutions.coreinvent.ui.login;

import androidx.core.content.ContextCompat;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.annotation.StringRes;
import androidx.appcompat.app.AppCompatActivity;

import android.preference.PreferenceManager;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.coresolutions.coreinvent.ui.main.DashboardActivity;
import com.coresolutions.coreinvent.R;
import com.google.android.material.button.MaterialButton;

import pl.aprilapps.easyphotopicker.EasyImage;

public class LoginActivity extends AppCompatActivity {

    private LoginViewModel loginViewModel;
    private ProgressDialog progressDialog;
    private SharedPreferences settings;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        settings = PreferenceManager.getDefaultSharedPreferences(this);
        loginViewModel = ViewModelProviders.of(this, new LoginViewModelFactory())
                .get(LoginViewModel.class);

        final EditText usernameEditText = findViewById(R.id.username);
        final EditText passwordEditText = findViewById(R.id.password);
        final MaterialButton loginButton = findViewById(R.id.login);


        loginViewModel.getLoginFormState().observe(this, new Observer<LoginFormState>() {
            @Override
            public void onChanged(@Nullable LoginFormState loginFormState) {
                if (loginFormState == null) {
                    return;
                }
                loginButton.setEnabled(loginFormState.isDataValid());
                if (loginFormState.isDataValid()) {
                    loginButton.setBackgroundColor(ContextCompat.getColor(LoginActivity.this, R.color.secondaryLightVariant));
                } else {
                    loginButton.setBackgroundColor(ContextCompat.getColor(LoginActivity.this, R.color.colorStroke));
                }
                if (loginFormState.getUsernameError() != null) {
                    usernameEditText.setError(getString(loginFormState.getUsernameError()));
                }
                if (loginFormState.getPasswordError() != null) {
//                    passwordEditText.setError(getString(loginFormState.getPasswordError()));
                }
            }
        });

        loginViewModel.getLoginResult().observe(this, new Observer<LoginResult>() {
            @Override
            public void onChanged(@Nullable LoginResult loginResult) {
                if (loginResult == null) {
                    progressDialog.dismiss();
                    return;
                }
                progressDialog.dismiss();
                if (loginResult.getError() != null) {
                    showLoginFailed(loginResult.getError());
                }
                if (loginResult.getSuccess() != null) {
                    Intent i = new Intent(LoginActivity.this, DashboardActivity.class);
                    SharedPreferences.Editor editor = settings.edit();
                    editor.putString("user_name", loginResult.getSuccess().getDisplayName());
                    editor.commit();
                    i.putExtra("user_name", loginResult.getSuccess().getDisplayName());
                    startActivity(i);
                    finish();
//                    updateUiWithUser(loginResult.getSuccess());
                }

//                setResult(Activity.RESULT_OK);
//                //Complete and destroy login activity once successful

            }
        });

        TextWatcher afterTextChangedListener = new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                // ignore
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                // ignore
            }

            @Override
            public void afterTextChanged(Editable s) {
                loginViewModel.loginDataChanged(usernameEditText.getText().toString(),
                        passwordEditText.getText().toString());
            }
        };
        usernameEditText.addTextChangedListener(afterTextChangedListener);
        passwordEditText.addTextChangedListener(afterTextChangedListener);
        passwordEditText.setOnEditorActionListener(new TextView.OnEditorActionListener() {

            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_DONE) {
                    progressDialog = new ProgressDialog(LoginActivity.this);
                    progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
                    progressDialog.setMessage("Conectando con el servidor...");
//                    progressDialog.setCancelable(false);
                    progressDialog.show();
                    loginViewModel.login(usernameEditText.getText().toString(),
                            passwordEditText.getText().toString(), settings);
                }
                return false;
            }
        });

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                loadingProgressBar.setVisibility(View.VISIBLE);
                progressDialog = new ProgressDialog(LoginActivity.this);
                progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
                progressDialog.setMessage("Conectando con el servidor...");
//                progressDialog.setCancelable(false);
                progressDialog.show();
                loginViewModel.login(usernameEditText.getText().toString(),
                        passwordEditText.getText().toString(), settings);
            }
        });
    }

    private void updateUiWithUser(LoggedInUserView model) {
        String welcome = getString(R.string.welcome) + model.getDisplayName();
        // TODO : initiate successful logged in experience
        Toast.makeText(getApplicationContext(), welcome, Toast.LENGTH_LONG).show();
    }

    private void showLoginFailed(@StringRes Integer errorString) {
        Toast.makeText(getApplicationContext(), errorString, Toast.LENGTH_SHORT).show();
    }
}
