package com.coresolutions.coreinvent.ui.baja;

import android.content.DialogInterface;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import com.coresolutions.coreinvent.R;
import com.coresolutions.coreinvent.data.pojos.FindAssetPojo;
import com.coresolutions.coreinvent.ui.alta.AltaActivity;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;

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

    @Override
    public void onBackPressed() {
        new MaterialAlertDialogBuilder(BajaActivity.this)
                .setTitle("Confirmación")
                .setMessage("¿Realmente desea descartar los cambios?")
                .setBackground(getDrawable(R.color.overBackground))
                .setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                })
                .setPositiveButton("Descartar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        BajaActivity.super.onBackPressed();
                    }
                }).show();
    }
}
