package com.coresolutions.coreinvent.ui.alta;

import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
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
import com.coresolutions.coreinvent.data.pojos.AssetPojo;
import com.coresolutions.coreinvent.data.pojos.Edifice;
import com.coresolutions.coreinvent.data.pojos.FieldPojo;
import com.coresolutions.coreinvent.data.pojos.Level;
import com.coresolutions.coreinvent.data.pojos.OptionPojo;
import com.coresolutions.coreinvent.data.pojos.Space;
import com.google.android.material.textfield.TextInputLayout;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * A simple {@link Fragment} subclass.
 */
public class LocationFragment extends Fragment {

    private ImageView back_img;
    private ImageView forward_img;
    private int subfamily;
    private AltaViewModel altaViewModel;
    private SharedPreferences settings;
    private TextInputLayout centerLayout;
    private AutoCompleteTextView center_dropdown;
    private TextInputLayout edificeLayout;
    private AutoCompleteTextView edifice_dropdown;
    private TextInputLayout levelLayout;
    private AutoCompleteTextView level_dropdown;
    private TextInputLayout spaceLayout;
    private AutoCompleteTextView space_dropdown;
    private OptionPojo optionPojo;
    private Edifice edifice;
    private Space space;
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

        centerLayout = view.findViewById(R.id.centerLayout);
        center_dropdown = view.findViewById(R.id.center_dropdown);
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
                selectedMap.put(R.string.center, center_dropdown.getText().toString());
                selectedMap.put(R.string.edifice, edifice_dropdown.getText().toString());
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
                if (!field.getOptionPojos().isEmpty() ) {
                    centerLayout.setVisibility(View.VISIBLE);
                    center_dropdown.setAdapter(new ArrayAdapter<>(getContext(), R.layout.dropdown_menu_popup_item, field.getOptionPojos()));
                } else {
                    if (getArguments().getString("from") != null) {
                        back_img.callOnClick();
                    } else {
                        forward_img.callOnClick();
                    }
                }
            } else if (field.getColumnName().equals("edifice")) {
                edificeLayout.setVisibility(View.VISIBLE);
            } else if (field.getColumnName().equals("level")) {
                levelLayout.setVisibility(View.VISIBLE);
            } else if (field.getColumnName().equals("space")) {
                spaceLayout.setVisibility(View.VISIBLE);
            }
        }


        center_dropdown.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                optionPojo = (OptionPojo) center_dropdown.getAdapter().getItem(position);
                assetPojo.setCenter(String.valueOf(optionPojo.getId()));
                edifice_dropdown.clearListSelection();
                edifice_dropdown.setText("");
                level_dropdown.clearListSelection();
                level_dropdown.setText("");
                space_dropdown.clearListSelection();
                space_dropdown.setText("");
                edifice_dropdown.setAdapter(new ArrayAdapter<>(getContext(), R.layout.dropdown_menu_popup_item, optionPojo.getEdifices()));
            }
        });

        edifice_dropdown.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                edifice = (Edifice) edifice_dropdown.getAdapter().getItem(position);
                assetPojo.setEdificeId(String.valueOf(edifice.getId()));
                level_dropdown.clearListSelection();
                level_dropdown.setText("");
                space_dropdown.clearListSelection();
                space_dropdown.setText("");
                level_dropdown.setAdapter(new ArrayAdapter<>(getContext(), R.layout.dropdown_menu_popup_item, edifice.getLevels()));
            }
        });

        level_dropdown.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Level level = (Level) level_dropdown.getAdapter().getItem(position);
                space_dropdown.clearListSelection();
                space_dropdown.setText("");
                space_dropdown.setAdapter(new ArrayAdapter<>(getContext(), R.layout.dropdown_menu_popup_item, level.getSpaces()));
            }
        });

        space_dropdown.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                space = (Space) space_dropdown.getAdapter().getItem(position);
                assetPojo.setSpace(String.valueOf(space.getId()));
            }
        });


    }
}
