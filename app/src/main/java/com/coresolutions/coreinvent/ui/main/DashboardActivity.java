package com.coresolutions.coreinvent.ui.main;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import android.Manifest;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.text.Layout;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.coresolutions.coreinvent.R;
import com.coresolutions.coreinvent.ui.login.LoginActivity;
import com.coresolutions.coreinvent.ui.login.LoginViewModel;
import com.coresolutions.coreinvent.ui.login.LoginViewModelFactory;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static androidx.fragment.app.DialogFragment.STYLE_NO_TITLE;

public class DashboardActivity extends AppCompatActivity {

    private FloatingActionButton newOperation;
    private LayoutInflater inflater;
    private PopupWindow popUp;
    private boolean menu = false;
    private OperationFragment operationFragment;
    private RelativeLayout session;
    private LoginViewModel loginViewModel;
    private SharedPreferences settings;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);
        loginViewModel = ViewModelProviders.of(this, new LoginViewModelFactory()).get(LoginViewModel.class);
        settings = PreferenceManager.getDefaultSharedPreferences(this);
        session = findViewById(R.id.session);
        newOperation = findViewById(R.id.newOperation);
        inflater = this.getLayoutInflater();
        View customView = inflater.inflate(R.layout.popup_logged_user, null);

        findViewById(R.id.add_photo).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent getIntent = new Intent(Intent.ACTION_GET_CONTENT);
                getIntent.setType("image/*");

                Intent pickIntent = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                pickIntent.setType("image/*");

                Intent chooserIntent = Intent.createChooser(getIntent, "Seleccione la imagen");
                chooserIntent.putExtra(Intent.EXTRA_INITIAL_INTENTS, new Intent[]{pickIntent});

                startActivityForResult(chooserIntent, 1);

                if (ActivityCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.CAMERA)
                        != PackageManager.PERMISSION_GRANTED) {
                    // Callback onRequestPermissionsResult interceptadona Activity MainActivity
                    ActivityCompat.requestPermissions(DashboardActivity.this, new String[]{Manifest.permission.CAMERA}, 2);
                } else {
                    // permission has been granted, continue as usual
                    Intent captureIntent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
                    startActivityForResult(captureIntent, 2);
                }
            }
        });

        LinearLayout close_seccion_layout = customView.findViewById(R.id.close_seccion_layout);
        close_seccion_layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String token = settings.getString("token", "");
                loginViewModel.logout(token).enqueue(new Callback<Void>() {
                    @Override
                    public void onResponse(Call<Void> call, Response<Void> response) {
                        SharedPreferences.Editor e = settings.edit();
                        e.putString("token", "");
                        e.commit();
                        Intent i = new Intent(getApplicationContext(), LoginActivity.class);
                        if (popUp.isShowing()) {
                            popUp.dismiss();
                        }
                        startActivity(i);
                        finish();
                    }

                    @Override
                    public void onFailure(Call<Void> call, Throwable t) {
                        Toast.makeText(getApplicationContext(), "No se puede conectar con el servidor", Toast.LENGTH_LONG).show();
                    }
                });
            }
        });

        findViewById(R.id.search_img).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(DashboardActivity.this, SearchActivity.class);
                startActivity(i);
            }
        });

        String user_name = getIntent().getStringExtra("user_name");
        ((TextView) customView.findViewById(R.id.display_name)).setText(user_name);
        popUp = new PopupWindow(customView, LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        popUp.setElevation(5.0f);
        session.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (popUp.isShowing()) {
                    popUp.dismiss();
                } else {
                    popUp.setInputMethodMode(PopupWindow.INPUT_METHOD_NEEDED);
                    popUp.setFocusable(true);
                    popUp.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                    popUp.showAsDropDown(session, 0, 0);
                }
            }
        });
        operationFragment = new OperationFragment();
//        final MaterialAlertDialogBuilder dialog = new MaterialAlertDialogBuilder(this)
//                .setBackground(getDrawable(R.color.background))
//                .setView(operation_dialog_view);
//
//        if (content.getWidth() > 0) {
//            Bitmap image = BlurBuilder.blur(content);
//            operation_dialog_view.setBackground((new BitmapDrawable(getResources(), image)));
//        } else {
//            content.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
//                @Override
//                public void onGlobalLayout() {
//                    Bitmap image = BlurBuilder.blur(content);
//                    operation_dialog_view.setBackground((new BitmapDrawable(getResources(), image)));
//                }
//            });
//        }


//        popUp = new PopupWindow(this);
//        popUp.setContentView(operation_dialog_view);

        newOperation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

//                View operation_dialog_view = inflater.inflate(R.layout.dialog_operation, null);
//
//                new MaterialAlertDialogBuilder(DashboardActivity.this)
//                        .setView(operation_dialog_view)
//                        .setBackground(getDrawable(R.color.background))
//                        .show();
//                if (!menu) {
                operationFragment.show(getSupportFragmentManager(), "OperationFragment");
                menu = true;
//                } else {
//                    DialogFragment dialogFragment = (DialogFragment) getSupportFragmentManager().findFragmentByTag("OperationFragment");
//                    if (dialogFragment != null) {
//                        dialogFragment.dismiss();
//                    }
//                    menu = false;
//                }


            }
        });


    }
}
