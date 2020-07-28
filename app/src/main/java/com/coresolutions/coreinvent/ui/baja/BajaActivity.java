package com.coresolutions.coreinvent.ui.baja;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import com.coresolutions.coreinvent.R;
import com.coresolutions.coreinvent.data.pojos.FindAssetPojo;

public class BajaActivity extends AppCompatActivity {

    private NavController navController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_baja);

        navController = Navigation.findNavController(BajaActivity.this, R.id.nav_host_fragment);


        if (getIntent().getSerializableExtra("asset") != null) {
            Bundle bundle = new Bundle();
            bundle.putSerializable("asset", (FindAssetPojo) getIntent().getSerializableExtra("asset"));
            navController.navigate(R.id.action_tag_fragment_to_reason_fragment, bundle);
        }
    }


}
