package com.coresolutions.coreinvent.ui.main;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.coresolutions.coreinvent.R;
import com.coresolutions.coreinvent.data.pojos.FindAssetPojo;
import com.coresolutions.coreinvent.data.pojos.Search;
import com.coresolutions.coreinvent.ui.alta.AltaViewModel;
import com.coresolutions.coreinvent.ui.login.LoginActivity;
import com.google.android.material.textfield.TextInputLayout;

import java.util.List;

public class SearchActivity extends AppCompatActivity implements AssetListAdapter.OnClickListener {
    private TextInputLayout findLayout;
    private RecyclerView recyclerView;
    private AltaViewModel altaViewModel;
    private SharedPreferences settings;
    private AssetListAdapter assetListAdapter;
    private ImageView search_img;
    private ImageView logo_img;
    private TextView selected_year;
    private ImageView dropdown_year;
    private ImageView close_search;
    private ConstraintLayout search_layout;
    private boolean searching;
    private ProgressDialog progressDialog;
    private String token;

    private DetailFragment detailFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        searching = false;
        settings = PreferenceManager.getDefaultSharedPreferences(this);
        altaViewModel =ViewModelProviders.of(this).get(AltaViewModel.class);
        token = settings.getString("access_token", "");
        assetListAdapter = new AssetListAdapter(this, this);
        search_img = findViewById(R.id.search_img);
        logo_img = findViewById(R.id.logo_img);
        selected_year = findViewById(R.id.selected_year);
        dropdown_year = findViewById(R.id.dropdown_year);
        close_search = findViewById(R.id.close_search);
        search_layout = findViewById(R.id.search_layout);

        findLayout = findViewById(R.id.findLayout);
        recyclerView = findViewById(R.id.recyclerAssetsDrawer);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(assetListAdapter);

        progressDialog = new ProgressDialog(this);
        progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progressDialog.setMessage("Cargando datos...");
        progressDialog.setCancelable(false);
        progressDialog.show();

        logo_img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        altaViewModel.getFindResult().observe(this, new Observer<List<FindAssetPojo>>() {
            @Override
            public void onChanged(List<FindAssetPojo> findAssetPojos) {
                assetListAdapter.setFindAssetPojos(findAssetPojos);
                progressDialog.dismiss();
            }
        });


        altaViewModel.findAsset(token, new Search(""));
        search_img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                logo_img.setVisibility(View.INVISIBLE);
                selected_year.setVisibility(View.INVISIBLE);
                dropdown_year.setVisibility(View.INVISIBLE);
                if (searching) {
                    progressDialog = new ProgressDialog(SearchActivity.this);
                    progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
                    progressDialog.setMessage("Cargando datos...");
                    progressDialog.setCancelable(false);
                    progressDialog.show();
                    altaViewModel.findAsset(settings.getString("access_token", ""), new Search(findLayout.getEditText().getText().toString()));
                }
                searching = true;
                search_layout.setVisibility(View.VISIBLE);

            }
        });

        close_search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                search_layout.setVisibility(View.INVISIBLE);
                logo_img.setVisibility(View.VISIBLE);
                selected_year.setVisibility(View.VISIBLE);
                dropdown_year.setVisibility(View.VISIBLE);
                findLayout.getEditText().setText("");
                searching = false;
                progressDialog = new ProgressDialog(SearchActivity.this);
                progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
                progressDialog.setMessage("Cargando datos...");
                progressDialog.setCancelable(false);
                progressDialog.show();
                altaViewModel.findAsset(settings.getString("access_token", ""), new Search(findLayout.getEditText().getText().toString()));
            }
        });
    }


    @Override
    public void OnClickListener(FindAssetPojo assets, int post) {
        detailFragment = DetailFragment.newInstance(assets.getId(), token);
        detailFragment.show(getSupportFragmentManager(), "details");
    }
}