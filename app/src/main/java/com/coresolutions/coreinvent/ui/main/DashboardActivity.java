package com.coresolutions.coreinvent.ui.main;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.Manifest;
import android.app.ProgressDialog;
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
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.coresolutions.coreinvent.R;
import com.coresolutions.coreinvent.data.pojos.Dashboard;
import com.coresolutions.coreinvent.data.pojos.Year;
import com.coresolutions.coreinvent.ui.alta.AltaViewModel;
import com.coresolutions.coreinvent.ui.login.LoginActivity;
import com.coresolutions.coreinvent.ui.login.LoginViewModel;
import com.coresolutions.coreinvent.ui.login.LoginViewModelFactory;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static androidx.fragment.app.DialogFragment.STYLE_NO_TITLE;

public class DashboardActivity extends AppCompatActivity implements YearListAdapter.OnClickListener {

    private FloatingActionButton newOperation;
    private LayoutInflater inflater;
    private PopupWindow popUp;
    private PopupWindow popUpYear;
    private boolean menu = false;
    private OperationFragment operationFragment;
    private RelativeLayout session;
    private LoginViewModel loginViewModel;
    private DashboardViewModel dashboardViewModel;
    private SharedPreferences settings;
    private ImageButton dropdown_year;
    private RecyclerView recyclerView;
    private YearListAdapter yearListAdapter;
    private String token;
    private TextView selected_year;

    private TextView sub_asset_count;
    private TextView sub_asset_value;
    private TextView sub_last_record;

    private TextView unsub_asset_count;
    private TextView unsub_asset_value;
    private TextView unsub_last_record;

    private TextView investment_value;

    private TextView cant_notific;

    private TextView critical;
    private TextView high;
    private TextView medium;
    private TextView low;
    private TextView none;
    private TextView risk_level;

    private ProgressDialog progressDialog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);


        sub_asset_count = findViewById(R.id.sub_asset_count);
        sub_asset_value = findViewById(R.id.sub_asset_value);
        sub_last_record = findViewById(R.id.sub_last_record);

        unsub_asset_count = findViewById(R.id.unsub_asset_count);
        unsub_asset_value = findViewById(R.id.unsub_asset_value);
        unsub_last_record = findViewById(R.id.unsub_last_record);

        investment_value = findViewById(R.id.investment_value);

        cant_notific = findViewById(R.id.cant_notific);

        critical = findViewById(R.id.critical);
        high = findViewById(R.id.high);
        medium = findViewById(R.id.medium);
        low = findViewById(R.id.low);
        none = findViewById(R.id.none);
        risk_level = findViewById(R.id.risk_level);

        progressDialog = new ProgressDialog(this);
        progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progressDialog.setMessage("Cargando datos...");
        progressDialog.setCancelable(false);


        loginViewModel = ViewModelProviders.of(this, new LoginViewModelFactory()).get(LoginViewModel.class);
        dashboardViewModel = ViewModelProviders.of(this).get(DashboardViewModel.class);
        settings = PreferenceManager.getDefaultSharedPreferences(this);
        token = settings.getString("access_token", "");
        selected_year = findViewById(R.id.selected_year);
        selected_year.setText(settings.getString("exercise_year_name", "Seleccione el año"));
        session = findViewById(R.id.session);
        dropdown_year = findViewById(R.id.dropdown_year);
        newOperation = findViewById(R.id.newOperation);
        yearListAdapter = new YearListAdapter(this, this);
        inflater = this.getLayoutInflater();
        View customView = inflater.inflate(R.layout.popup_logged_user, null);
        View customViewYear = inflater.inflate(R.layout.popup_years, null);
        recyclerView = customViewYear.findViewById(R.id.recyclerYears);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(yearListAdapter);
        dashboardViewModel.getYearsResult().observe(this, new Observer<List<Year>>() {
            @Override
            public void onChanged(List<Year> years) {
                yearListAdapter.setYears(years);
                if (settings.getInt("exercise_year_id", 0) == 0 && years != null) {
                    dashboardViewModel.getDashboardInfo(token, years.get(0).getId());
                }
                progressDialog.dismiss();
            }
        });


        dashboardViewModel.getDashResult().observe(this, new Observer<Dashboard>() {
            @Override
            public void onChanged(Dashboard dashboard) {
                if (dashboard != null) {
                    sub_asset_count.setText(String.valueOf(dashboard.getSubscription().getAssetCount()));
                    sub_asset_value.setText(String.valueOf(dashboard.getSubscription().getAssetValue()));
                    sub_last_record.setText(dashboard.getSubscription().getLastRecord() != null ? dashboard.getSubscription().getLastRecord().getCode() : "---");

                    unsub_asset_count.setText(String.valueOf(dashboard.getUnsubscriptionInfo().getAssetCount()));
                    unsub_asset_value.setText(String.valueOf(dashboard.getUnsubscriptionInfo().getAssetValue()));
                    unsub_last_record.setText(dashboard.getUnsubscriptionInfo().getLastRecord() != null ? dashboard.getUnsubscriptionInfo().getLastRecord().getCode() : "---");

                    investment_value.setText(String.valueOf(dashboard.getInvestments().getInvestmentValue()));
                    cant_notific.setText(String.valueOf(dashboard.getNotifications().size()));

                    critical.setText(String.valueOf(dashboard.getRisks().getRiskSummary().getCritical()));
                    high.setText(String.valueOf(dashboard.getRisks().getRiskSummary().getHigh()));
                    medium.setText(String.valueOf(dashboard.getRisks().getRiskSummary().getMedium()));
                    low.setText(String.valueOf(dashboard.getRisks().getRiskSummary().getLow()));
                    none.setText(String.valueOf(dashboard.getRisks().getRiskSummary().getNone()));

                    switch (dashboard.getRisks().getRiskLevel()) {
                        case "none":
                            risk_level.setText(R.string.none);
                            break;
                        case "low":
                            risk_level.setText(R.string.low);
                            break;
                        case "medium":
                            risk_level.setText(R.string.medium);
                            break;
                        case "high":
                            risk_level.setText(R.string.high);
                            break;
                        case "critical":
                            risk_level.setText(R.string.critical);
                            break;
                    }

                }

                progressDialog.dismiss();
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

        customView.findViewById(R.id.refresh_layout).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                update_dashboard();
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
        popUpYear = new PopupWindow(customViewYear, LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        popUp.setElevation(5.0f);
        popUpYear.setElevation(5.0f);
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

        dropdown_year.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (popUpYear.isShowing()) {
                    popUpYear.dismiss();
                } else {
                    popUpYear.setInputMethodMode(PopupWindow.INPUT_METHOD_NEEDED);
                    popUpYear.setFocusable(true);
                    popUpYear.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                    popUpYear.showAsDropDown(dropdown_year, 0, 0);
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


        update_dashboard();
    }

    @Override
    public void OnClickListener(Year years, int post) {
        SharedPreferences.Editor editor = settings.edit();
        editor.putString("exercise_year_name", years.getName());
        editor.putInt("exercise_year_id", years.getId());
        editor.commit();
        selected_year.setText(years.getName());
        popUpYear.dismiss();
        progressDialog.show();
        dashboardViewModel.getDashboardInfo(token, years.getId());
    }


    private void update_dashboard() {
        progressDialog.show();
        dashboardViewModel.getYears(token);
        if (settings.getInt("exercise_year_id", 0) != 0) {
            dashboardViewModel.getDashboardInfo(token, settings.getInt("exercise_year_id", 0));
        }
    }
}
