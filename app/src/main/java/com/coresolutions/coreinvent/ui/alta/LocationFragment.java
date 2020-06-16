package com.coresolutions.coreinvent.ui.alta;

import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.navigation.Navigation;

import android.preference.PreferenceManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.ImageView;

import com.coresolutions.coreinvent.R;
import com.coresolutions.coreinvent.ui.alta.pojos.AssetPojo;
import com.coresolutions.coreinvent.ui.alta.pojos.EdificePojo;
import com.coresolutions.coreinvent.ui.alta.pojos.FieldPojo;
import com.coresolutions.coreinvent.ui.alta.pojos.LevelPojo;
import com.coresolutions.coreinvent.ui.alta.pojos.SpacePojo;
import com.google.android.material.textfield.TextInputLayout;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class LocationFragment extends Fragment {

    private ImageView back_img;
    private ImageView forward_img;
    private int subfamily;
    private AltaViewModel altaViewModel;
    private SharedPreferences settings;
    private TextInputLayout edificeLayout;
    private AutoCompleteTextView edifice_dropdown;
    private TextInputLayout levelLayout;
    private AutoCompleteTextView level_dropdown;
    private TextInputLayout spaceLayout;
    private AutoCompleteTextView space_dropdown;
    private EdificePojo edificePojo;
    private SpacePojo spacePojo;
    private ArrayList<FieldPojo> fieldPojoArrayList;
    private AssetPojo assetPojo;


    public LocationFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_location, container, false);
    }


    @Override
    public void onViewCreated(@NonNull final View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        edificeLayout = view.findViewById(R.id.edificeLayout);
        edifice_dropdown = view.findViewById(R.id.edifice_dropdown);
        levelLayout = view.findViewById(R.id.levelLayout);
        level_dropdown = view.findViewById(R.id.level_dropdown);
        spaceLayout = view.findViewById(R.id.spaceLayout);
        space_dropdown = view.findViewById(R.id.space_dropdown);
        back_img = view.findViewById(R.id.back_img);
        forward_img = view.findViewById(R.id.forward_img);


        settings = PreferenceManager.getDefaultSharedPreferences(getContext());
        altaViewModel = ViewModelProviders.of(this).get(AltaViewModel.class);

        assetPojo = (AssetPojo) getArguments().getSerializable("assetPojo");

        subfamily = getArguments().getInt("subfamily");

        back_img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle = new Bundle();
                HashMap<Integer, String> selectedMap = (HashMap<Integer, String>) getArguments().getSerializable("selectedMap");
                bundle.putInt("subfamily", getArguments().getInt("subfamily"));
                bundle.putSerializable("assetPojo", assetPojo);
                bundle.putSerializable("selectedMap", selectedMap);
                Navigation.findNavController(v).navigate(R.id.action_nav_location_to_nav_scan, bundle);
            }
        });

        forward_img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle = new Bundle();
                HashMap<Integer, String> selectedMap = (HashMap<Integer, String>) getArguments().getSerializable("selectedMap");
                selectedMap.put(R.string.center, edifice_dropdown.getText().toString());
                selectedMap.put(R.string.level, level_dropdown.getText().toString());
                selectedMap.put(R.string.space, space_dropdown.getText().toString());
                bundle.putSerializable("fieldPojos", fieldPojoArrayList);
                bundle.putInt("subfamily", getArguments().getInt("subfamily"));
                bundle.putSerializable("assetPojo", assetPojo);
                bundle.putSerializable("selectedMap", selectedMap);
                Navigation.findNavController(v).navigate(R.id.action_nav_location_to_nav_properties, bundle);
            }
        });

        fieldPojoArrayList = (ArrayList<FieldPojo>) getArguments().getSerializable("fieldPojos");
        for (FieldPojo field : fieldPojoArrayList) {
            if (field.getColumnName().equals("center")) {
                if (field.getOptionPojos().get(0).getEdificePojos() != null) {
                    edificeLayout.setVisibility(View.VISIBLE);
                    levelLayout.setVisibility(View.VISIBLE);
                    spaceLayout.setVisibility(View.VISIBLE);
                    edifice_dropdown.setAdapter(new ArrayAdapter<>(getContext(), R.layout.dropdown_menu_popup_item, field.getOptionPojos().get(0).getEdificePojos()));
                } else {
                    if (getArguments().getString("from") != null) {
                        back_img.callOnClick();
                    } else {
                        forward_img.callOnClick();
                    }
                }
            }
        }


        edifice_dropdown.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                edificePojo = (EdificePojo) edifice_dropdown.getAdapter().getItem(position);
                assetPojo.setEdificeId(String.valueOf(edificePojo.getId()));
                level_dropdown.clearListSelection();
                level_dropdown.setText("");
                space_dropdown.clearListSelection();
                space_dropdown.setText("");
                level_dropdown.setAdapter(new ArrayAdapter<>(getContext(), R.layout.dropdown_menu_popup_item, edificePojo.getLevelPojos()));
            }
        });

        level_dropdown.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                LevelPojo levelPojo = (LevelPojo) level_dropdown.getAdapter().getItem(position);
                space_dropdown.clearListSelection();
                space_dropdown.setText("");
                space_dropdown.setAdapter(new ArrayAdapter<>(getContext(), R.layout.dropdown_menu_popup_item, levelPojo.getSpacePojos()));
            }
        });

        space_dropdown.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                spacePojo = (SpacePojo) space_dropdown.getAdapter().getItem(position);
                assetPojo.setSpace(String.valueOf(spacePojo.getId()));
            }
        });


    }
}
