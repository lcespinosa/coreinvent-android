package com.coresolutions.coreinvent.ui.alta;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.coresolutions.coreinvent.R;
import com.coresolutions.coreinvent.data.pojos.AssetPojo;
import com.coresolutions.coreinvent.data.pojos.FieldPojo;
import com.google.android.material.textfield.TextInputEditText;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * A simple {@link Fragment} subclass.
 */
public class DescriptionFragment extends Fragment {


    private ImageView back_img;
    private ImageView forward_img;
    private TextInputEditText observations;
    private AssetPojo assetPojo;
    private ArrayList<FieldPojo> fieldPojoArrayList;

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

        back_img = view.findViewById(R.id.back_img);
        forward_img = view.findViewById(R.id.forward_img);
        observations = view.findViewById(R.id.observations);

        fieldPojoArrayList = (ArrayList<FieldPojo>) getArguments().getSerializable("fieldPojos");
        assetPojo = (AssetPojo) getArguments().getSerializable("assetPojo");

        back_img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle = new Bundle();
                HashMap<Integer, String> selectedMap = (HashMap<Integer, String>) getArguments().getSerializable("selectedMap");
                bundle.putInt("subfamily", getArguments().getInt("subfamily"));
                bundle.putSerializable("fieldPojos", fieldPojoArrayList);
                bundle.putSerializable("assetPojo", assetPojo);
                bundle.putSerializable("selectedMap", selectedMap);
                Navigation.findNavController(v).navigate(R.id.action_nav_description_to_nav_properties, bundle);
            }
        });

        forward_img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                assetPojo.setObservations(observations.getText().toString());
                Bundle bundle = new Bundle();
                HashMap<Integer, String> selectedMap = (HashMap<Integer, String>) getArguments().getSerializable("selectedMap");
                bundle.putInt("subfamily", getArguments().getInt("subfamily"));
                bundle.putSerializable("fieldPojos", fieldPojoArrayList);
                bundle.putSerializable("assetPojo", assetPojo);
                bundle.putSerializable("selectedMap", selectedMap);
                Navigation.findNavController(v).navigate(R.id.action_nav_description_to_nav_confimation, bundle);
            }
        });
    }
}
