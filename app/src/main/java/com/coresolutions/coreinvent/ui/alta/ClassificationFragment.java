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
import android.widget.Toast;

import com.coresolutions.coreinvent.R;
import com.coresolutions.coreinvent.ui.alta.pojos.FamilyPojo;
import com.coresolutions.coreinvent.ui.alta.pojos.SubFamilyPojo;

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
    private SharedPreferences settings;
    private SubFamilyPojo subFamilyPojo;

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

        altaViewModel.getFamilyResult().observe(this, new Observer<List<FamilyPojo>>() {
            @Override
            public void onChanged(List<FamilyPojo> familyPojos) {
                if (familyPojos != null) {
                    family_dropdown.setAdapter(new ArrayAdapter<>(getContext(), R.layout.dropdown_menu_popup_item, familyPojos));
                } else {
                    Toast.makeText(getContext(), "Error", Toast.LENGTH_LONG).show();
                }
            }
        });

        family_dropdown.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                FamilyPojo familyPojo = (FamilyPojo) family_dropdown.getAdapter().getItem(position);
                sub_family_dropdown.clearListSelection();
                sub_family_dropdown.setText("");
                type_dropdown.clearListSelection();
                type_dropdown.setText("");
                sub_family_dropdown.setAdapter(new ArrayAdapter<>(getContext(), R.layout.dropdown_menu_popup_item, familyPojo.getSubFamilies()));
            }
        });

        sub_family_dropdown.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                type_dropdown.clearListSelection();
                type_dropdown.setText("");
                subFamilyPojo = (SubFamilyPojo) sub_family_dropdown.getAdapter().getItem(position);
                type_dropdown.setAdapter(new ArrayAdapter<>(getContext(), R.layout.dropdown_menu_popup_item, subFamilyPojo.getTypePojos()));
            }
        });

        altaViewModel.getFamily(settings.getString("access_token", ""));

        back_img = view.findViewById(R.id.back_img);
        forward_img = view.findViewById(R.id.forward_img);
        back_img.setVisibility(View.INVISIBLE);

        forward_img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle = new Bundle();
                bundle.putInt("subfamily", subFamilyPojo.getId());
                Navigation.findNavController(v).navigate(R.id.action_nav_home_to_nav_scan, bundle);
            }
        });
    }
}
