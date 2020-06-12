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
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.ImageView;
import android.widget.Toast;

import com.coresolutions.coreinvent.R;

import java.util.Collections;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class ClassificationFragment extends Fragment {

    private ImageView back_img;
    private ImageView forward_img;
    private AutoCompleteTextView family_dropdown;
    private AutoCompleteTextView sub_family_dropdown;
    private AutoCompleteTextView type_dropdown;
    private AltaViewModel altaViewModel;
    private List<FamilyPojo> familys;
    private SharedPreferences settings;

    public ClassificationFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_classification, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        settings = PreferenceManager.getDefaultSharedPreferences(getContext());
        altaViewModel = ViewModelProviders.of(this).get(AltaViewModel.class);
        family_dropdown = view.findViewById(R.id.family_dropdown);
        sub_family_dropdown = view.findViewById(R.id.sub_family_dropdown);
        type_dropdown = view.findViewById(R.id.type_dropdown);

        String[] COUNTRIES = new String[]{"Item 1", "Item 2", "Item 3", "Item 4"};
        ArrayAdapter<String> adapter =
                new ArrayAdapter<>(getContext(), R.layout.dropdown_menu_popup_item, COUNTRIES);


        altaViewModel.getFamilyResult().observe(this, new Observer<List<FamilyPojo>>() {
            @Override
            public void onChanged(List<FamilyPojo> familyPojos) {
                if (familyPojos != null) {
                    familys = familyPojos;
                    List<String> familyNames = Collections.emptyList();
                    for (FamilyPojo family : familyPojos) {
                        familyNames.add(family.getName());
                    }
                    family_dropdown.setAdapter(new ArrayAdapter<>(getContext(), R.layout.dropdown_menu_popup_item, familyNames));
                } else {
                    Toast.makeText(getContext(), "Error", Toast.LENGTH_LONG).show();
                }
            }
        });

        altaViewModel.getFamily(settings.getString("access_token", ""));

        back_img = view.findViewById(R.id.back_img);
        forward_img = view.findViewById(R.id.forward_img);
        back_img.setVisibility(View.INVISIBLE);

        forward_img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Navigation.findNavController(v).navigate(R.id.action_nav_home_to_nav_scan);
            }
        });
    }
}
