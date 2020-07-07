package com.coresolutions.coreinvent.ui.alta;

import android.app.ProgressDialog;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.navigation.Navigation;

import android.preference.PreferenceManager;
import android.text.InputType;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.Toast;

import com.coresolutions.coreinvent.R;
import com.coresolutions.coreinvent.data.pojos.AssetPojo;
import com.coresolutions.coreinvent.data.pojos.FieldPojo;
import com.coresolutions.coreinvent.data.pojos.OptionPojo;
import com.coresolutions.coreinvent.data.pojos.Tag;
import com.google.android.material.switchmaterial.SwitchMaterial;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

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
    private TextInputLayout tagLayout;
    private SwitchMaterial virtualTagSwitch;
    private SharedPreferences settings;
    private AltaViewModel altaViewModel;
    private int subfamily;
    private String typeTag;
    private ArrayList<FieldPojo> fieldPojoArrayList;
    private ProgressDialog progressDialog;

    public ScanFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_scan, container, false);
    }


    @Override
    public void onViewCreated(@NonNull final View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        subfamily = getArguments().getInt("subfamily");
        settings = PreferenceManager.getDefaultSharedPreferences(getContext());
        altaViewModel = ViewModelProviders.of(this).get(AltaViewModel.class);
        typeTag = "real";
        progressDialog = new ProgressDialog(getContext());
        progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progressDialog.setMessage("Cargando datos...");
        progressDialog.setCancelable(false);
        progressDialog.show();

        altaViewModel.getRegisterResult().observe(this, new Observer<List<FieldPojo>>() {
            @Override
            public void onChanged(List<FieldPojo> fieldPojos) {
                fieldPojoArrayList = new ArrayList<FieldPojo>(fieldPojos);
                progressDialog.dismiss();
            }
        });

        altaViewModel.getFields(subfamily, settings.getString("access_token", ""));


        altaViewModel.getFamily(settings.getString("access_token", ""));

        virtualTagSwitch = view.findViewById(R.id.virtualTagSwitch);
        tag = view.findViewById(R.id.tag);
        tagLayout = view.findViewById(R.id.tagLayout);
        back_img = view.findViewById(R.id.back_img);
        forward_img = view.findViewById(R.id.forward_img);
        back_img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Navigation.findNavController(v).navigate(R.id.action_nav_scan_to_nav_home);
            }
        });

        virtualTagSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
//                    tag.setFocusable(false);
                    typeTag = "madeup";
                    tag.setInputType(0);
                    tag.setText(findFirstVirtualTag(fieldPojoArrayList));
                } else {
//                    tagLayout.setFocusable(true);
                    tag.setInputType(InputType.TYPE_CLASS_TEXT);
                    typeTag = "real";
                    tag.setText("");
                }
            }
        });


        forward_img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String tagcode = tag.getText().toString();
                int tagId = findIdTag(fieldPojoArrayList, tagcode, typeTag);
                if (tagId != -1) {
                    Bundle bundle = new Bundle();
                    AssetPojo assetPojo = (AssetPojo) getArguments().getSerializable("assetPojo");
                    HashMap<String, String> selectedMap = (HashMap<String, String>) getArguments().getSerializable("selectedMap");
                    selectedMap.put("tag", tag.getText().toString());
                    bundle.putSerializable("fieldPojos", fieldPojoArrayList);
                    assetPojo.setTag(String.valueOf(tagId));
                    assetPojo.setTagType(virtualTagSwitch.isChecked() ? "3" : "2");
                    bundle.putInt("subfamily", subfamily);
                    bundle.putSerializable("assetPojo", assetPojo);
                    bundle.putSerializable("selectedMap", selectedMap);
//                    Navigation.findNavController(v).navigate(R.id.action_nav_scan_to_nav_location, bundle);
                } else {
                    Toast.makeText(getContext(), "La etiqueta seleccionada no es válida", Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    private int findIdTag(List<FieldPojo> fieldPojos, String tag, String typeTag) {
        for (FieldPojo field : fieldPojos) {
            if (field.getColumnName().equals("tag_type")) {
                for (OptionPojo option : field.getOptionPojos()) {
                    if (option.getCode().equals(typeTag)) {
                        for (Tag tagPojo : option.getTags()) {
                            if (tagPojo.getCode().equals(tag)) {
                                return tagPojo.getId();
                            }
                        }
                    }

                }
            }
        }
        return -1;
    }

    private String findFirstVirtualTag(List<FieldPojo> fieldPojos) {
        for (FieldPojo field : fieldPojos) {
            if (field.getColumnName().equals("tag_type")) {
                for (OptionPojo option : field.getOptionPojos()) {
                    if (option.getCode().equals("madeup")) {
                        if (!option.getTags().isEmpty()) {
                            return option.getTags().get(0).getCode();
                        }
                    }

                }
            }
        }
        return "";
    }
}
