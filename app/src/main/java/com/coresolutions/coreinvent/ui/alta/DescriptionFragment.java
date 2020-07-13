package com.coresolutions.coreinvent.ui.alta;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.navigation.Navigation;

import android.os.Environment;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.coresolutions.coreinvent.R;
import com.coresolutions.coreinvent.data.pojos.AssetPojo;
import com.coresolutions.coreinvent.data.pojos.FieldPojo;
import com.coresolutions.coreinvent.ui.main.DashboardActivity;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.textfield.TextInputEditText;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import pl.aprilapps.easyphotopicker.ChooserType;
import pl.aprilapps.easyphotopicker.DefaultCallback;
import pl.aprilapps.easyphotopicker.EasyImage;
import pl.aprilapps.easyphotopicker.MediaFile;
import pl.aprilapps.easyphotopicker.MediaSource;

/**
 * A simple {@link Fragment} subclass.
 */
public class DescriptionFragment extends Fragment {


    private ImageView back_img;
    private ImageView forward_img;
    private ImageView asset_img;
    private TextInputEditText observations;
    private AssetPojo assetPojo;
    private ArrayList<FieldPojo> fieldPojoArrayList;
    private FloatingActionButton camera;

    public DescriptionFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_description, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        AltaActivity act = (AltaActivity) getActivity();
        back_img = view.findViewById(R.id.back_img);
        forward_img = view.findViewById(R.id.forward_img);
        observations = view.findViewById(R.id.observations);
        asset_img = view.findViewById(R.id.asset_img);
        camera = view.findViewById(R.id.camera);

//        checkGalleryAppAvailability();
        fieldPojoArrayList = (ArrayList<FieldPojo>) getArguments().getSerializable("fieldPojos");
        assetPojo = (AssetPojo) getArguments().getSerializable("assetPojo");

        camera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((AltaActivity) getActivity()).ChooseImage();
            }
        });


        act.getModel().getImage().observe(this, new Observer<File>() {
            @Override
            public void onChanged(File file) {
                Bitmap bitmap = BitmapFactory.decodeFile(file.getAbsolutePath());
                asset_img.setImageBitmap(bitmap);
                asset_img.setVisibility(View.VISIBLE);
                assetPojo.setImage(file);
            }
        });


        back_img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().onBackPressed();
            }
        });

        forward_img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                assetPojo.setObservations(observations.getText().toString());
                Bundle bundle = new Bundle();
                HashMap<String, String> selectedMap = (HashMap<String, String>) getArguments().getSerializable("selectedMap");
                selectedMap.put("description", observations.getText().toString());
                bundle.putInt("subfamily", getArguments().getInt("subfamily"));
                bundle.putSerializable("fieldPojos", fieldPojoArrayList);
                bundle.putSerializable("assetPojo", assetPojo);
                bundle.putSerializable("selectedMap", selectedMap);
                Navigation.findNavController(v).navigate(R.id.action_nav_description_to_nav_confimation, bundle);
            }
        });
    }


}
