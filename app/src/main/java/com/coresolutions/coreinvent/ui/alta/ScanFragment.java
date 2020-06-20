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
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.Toast;

import com.coresolutions.coreinvent.R;
import com.coresolutions.coreinvent.ui.alta.pojos.AssetPojo;
import com.coresolutions.coreinvent.ui.alta.pojos.FamilyPojo;
import com.coresolutions.coreinvent.ui.alta.pojos.FieldListPojo;
import com.coresolutions.coreinvent.ui.alta.pojos.FieldPojo;
import com.coresolutions.coreinvent.ui.alta.pojos.OptionPojo;
import com.google.android.material.switchmaterial.SwitchMaterial;
import com.google.android.material.textfield.TextInputEditText;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class ScanFragment extends Fragment {
    private ImageView back_img;
    private ImageView forward_img;
    private TextInputEditText tag;
    private SwitchMaterial virtualTagSwitch;
    private SharedPreferences settings;
    private AltaViewModel altaViewModel;
    private int subfamily;
    private ArrayList<FieldPojo> fieldPojoArrayList;

    public ScanFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_scan, container, false);
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        subfamily = getArguments().getInt("subfamily");
        settings = PreferenceManager.getDefaultSharedPreferences(getContext());
        altaViewModel = ViewModelProviders.of(this).get(AltaViewModel.class);

        altaViewModel.getRegisterResult().observe(this, new Observer<FieldListPojo>() {
            @Override
            public void onChanged(FieldListPojo fieldListPojo) {
                fieldPojoArrayList = new ArrayList<FieldPojo>(fieldListPojo.getFields());
            }
        });

        altaViewModel.getFields(subfamily, settings.getString("access_token", ""));


        altaViewModel.getFamily(settings.getString("access_token", ""));

        virtualTagSwitch = view.findViewById(R.id.virtualTagSwitch);
        tag = view.findViewById(R.id.tag);
        back_img = view.findViewById(R.id.back_img);
        forward_img = view.findViewById(R.id.forward_img);
        back_img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Navigation.findNavController(v).navigate(R.id.action_nav_scan_to_nav_home);
            }
        });

        virtualTagSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {

                }
            }
        });


        forward_img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String tagcode = tag.getText().toString();
                int tagId = findIdTag(fieldPojoArrayList, tagcode);
                if (tagId != -1) {
                    Bundle bundle = new Bundle();
                    AssetPojo assetPojo = (AssetPojo) getArguments().getSerializable("assetPojo");
                    HashMap<Integer, String> selectedMap = (HashMap<Integer, String>) getArguments().getSerializable("selectedMap");
                    selectedMap.put(R.string.tag, tag.getText().toString());
                    bundle.putSerializable("fieldPojos", fieldPojoArrayList);
                    assetPojo.setTag(String.valueOf(tagId));
                    assetPojo.setTagType(virtualTagSwitch.isChecked() ? "3" : "2");
                    bundle.putInt("subfamily", subfamily);
                    bundle.putSerializable("assetPojo", assetPojo);
                    bundle.putSerializable("selectedMap", selectedMap);
                    Navigation.findNavController(v).navigate(R.id.action_nav_scan_to_nav_location, bundle);
                } else {
                    Toast.makeText(getContext(), "La etiqueta seleccionada no es válida", Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    private int findIdTag(List<FieldPojo> fieldPojos, String tag) {
        for (FieldPojo field : fieldPojos) {
            if (field.getColumnName().equals("tag")) {
                for (OptionPojo option : field.getOptionPojos()) {
                    if (option.getCode().equals(tag)) {
                        return option.getId();
                    }
                }
            }
        }
        return -1;
    }
}
