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
import android.widget.AutoCompleteTextView;
import android.widget.ImageView;

import com.coresolutions.coreinvent.R;
import com.coresolutions.coreinvent.ui.alta.pojos.FieldPojo;
import com.google.android.material.textfield.TextInputLayout;

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
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        edificeLayout = view.findViewById(R.id.edificeLayout);
        edifice_dropdown = view.findViewById(R.id.edifice_dropdown);
        levelLayout = view.findViewById(R.id.levelLayout);
        level_dropdown = view.findViewById(R.id.level_dropdown);
        spaceLayout = view.findViewById(R.id.spaceLayout);
        space_dropdown = view.findViewById(R.id.space_dropdown);

        settings = PreferenceManager.getDefaultSharedPreferences(getContext());
        altaViewModel = ViewModelProviders.of(this).get(AltaViewModel.class);

        subfamily = getArguments().getInt("subfamily");

        altaViewModel.getRegisterResult().observe(this, new Observer<List<FieldPojo>>() {
            @Override
            public void onChanged(List<FieldPojo> fieldPojos) {
                for (FieldPojo field : fieldPojos) {

                }
            }
        });

        altaViewModel.getFields(subfamily, settings.getString("access_token", ""));

        back_img = view.findViewById(R.id.back_img);
        forward_img = view.findViewById(R.id.forward_img);

        back_img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Navigation.findNavController(v).navigate(R.id.action_nav_location_to_nav_scan);
            }
        });

        forward_img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Navigation.findNavController(v).navigate(R.id.action_nav_location_to_nav_properties);
            }
        });
    }
}
