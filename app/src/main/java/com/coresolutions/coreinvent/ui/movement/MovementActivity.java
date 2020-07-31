package com.coresolutions.coreinvent.ui.movement;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.content.DialogInterface;
import android.os.Bundle;

import com.coresolutions.coreinvent.R;
import com.coresolutions.coreinvent.data.pojos.FindAssetPojo;
import com.coresolutions.coreinvent.ui.baja.BajaActivity;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;

public class MovementActivity extends AppCompatActivity {

    private NavController navController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movement);

        navController = Navigation.findNavController(MovementActivity.this, R.id.nav_host_fragment);

        if (getIntent().getSerializableExtra("asset") != null) {
            Bundle bundle = new Bundle();
            bundle.putSerializable("asset", (FindAssetPojo) getIntent().getSerializableExtra("asset"));
            navController.navigate(R.id.action_movement_tag_fragment_to_movement_location_fragment, bundle);
        }

    }

    @Override
    public void onBackPressed() {
        new MaterialAlertDialogBuilder(MovementActivity.this)
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
                        MovementActivity.super.onBackPressed();
                    }
                }).show();
    }
}
