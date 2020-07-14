package com.coresolutions.coreinvent.ui.alta;

import android.app.DatePickerDialog;
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
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.DatePicker;
import android.widget.ImageView;

import com.coresolutions.coreinvent.R;
import com.coresolutions.coreinvent.data.pojos.AssetModel;
import com.coresolutions.coreinvent.data.pojos.AssetPojo;
import com.coresolutions.coreinvent.data.pojos.Edifice;
import com.coresolutions.coreinvent.data.pojos.FieldPojo;
import com.coresolutions.coreinvent.data.pojos.Level;
import com.coresolutions.coreinvent.data.pojos.OptionPojo;
import com.coresolutions.coreinvent.data.pojos.Space;
import com.coresolutions.coreinvent.data.pojos.Tag;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class PropertiesFragment extends Fragment implements DatePickerDialog.OnDateSetListener {


    private ImageView back_img;
    private ImageView forward_img;
    private ArrayList<FieldPojo> fieldPojoArrayList;
    private AssetPojo assetPojo;
    private TextInputLayout brandLayout;
    private TextInputLayout modelLayout;
    private TextInputLayout serieLayout;
    private TextInputLayout characteristicsLayout;
    private TextInputLayout plateLayout;
    private TextInputLayout frame_numberLayout;
    private TextInputLayout widthLayout;
    private TextInputLayout heightLayout;
    private TextInputLayout depthLayout;
    private TextInputLayout dateLayout;
    private TextInputLayout addressLayout;
    private TextInputLayout postal_codeLayout;
    private TextInputLayout surfaceLayout;
    private TextInputLayout lengthLayout;
    private AutoCompleteTextView brand_dropdown;
    private AutoCompleteTextView model_dropdown;
    private TextInputEditText serie_dropdown;
    private TextInputEditText characteristics_dropdown;
    private TextInputEditText plate_dropdown;
    private TextInputEditText frame_number_dropdown;
    private TextInputEditText width_dropdown;
    private TextInputEditText height_dropdown;
    private TextInputEditText depth_dropdown;
    private TextInputEditText date_dropdown;
    private TextInputEditText address_dropdown;
    private TextInputEditText postal_code_dropdown;
    private TextInputEditText surface_dropdown;
    private TextInputEditText length_dropdown;
    private DatePickerDialog datePickerDialog;

    private TextInputLayout centerLayout;
    private AutoCompleteTextView center_dropdown;
    private TextInputLayout edificeLayout;
    private AutoCompleteTextView edifice_dropdown;
    private TextInputLayout levelLayout;
    private AutoCompleteTextView level_dropdown;
    private TextInputLayout spaceLayout;
    private AutoCompleteTextView space_dropdown;

    private TextInputLayout realTagLayout;
    private TextInputLayout virtualTagLayout;
    private String typeTag;
    private AltaViewModel altaViewModel;
    private ProgressDialog progressDialog;
    private int subfamily;
    private SharedPreferences settings;

    private Calendar cal;

    public PropertiesFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_properties, container, false);
    }

    @Override
    public void onViewCreated(@NonNull final View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        settings = PreferenceManager.getDefaultSharedPreferences(getContext());
        altaViewModel = ViewModelProviders.of(this).get(AltaViewModel.class);
        subfamily = getArguments().getInt("subfamily");
        progressDialog = new ProgressDialog(getContext());
        progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progressDialog.setMessage("Cargando datos...");
        progressDialog.setCancelable(false);
        progressDialog.show();


        altaViewModel.getRegisterResult().observe(this, new Observer<List<FieldPojo>>() {
            @Override
            public void onChanged(List<FieldPojo> fieldPojos) {
                fieldPojoArrayList = new ArrayList<FieldPojo>(fieldPojos);
                for (FieldPojo field : fieldPojoArrayList) {

//            if (field.getColumnName().equals("brand") && !field.getOptionPojos().isEmpty()) {
                    if (field.getColumnName().equals("brand")) {
                        brandLayout.setVisibility(View.VISIBLE);
                        modelLayout.setVisibility(View.VISIBLE);
                        brand_dropdown.setAdapter(new ArrayAdapter<>(getContext(), R.layout.dropdown_menu_popup_item, field.getOptionPojos()));
                    } else if (field.getColumnName().equals("serial_number")) {
                        serieLayout.setVisibility(View.VISIBLE);
                    } else if (field.getColumnName().equals("characteristics")) {
                        characteristicsLayout.setVisibility(View.VISIBLE);
                    } else if (field.getColumnName().equals("plate")) {
                        plateLayout.setVisibility(View.VISIBLE);
                    } else if (field.getColumnName().equals("frame_number")) {
                        frame_numberLayout.setVisibility(View.VISIBLE);
                    } else if (field.getColumnName().equals("width")) {
                        widthLayout.setVisibility(View.VISIBLE);
                    } else if (field.getColumnName().equals("height")) {
                        heightLayout.setVisibility(View.VISIBLE);
                    } else if (field.getColumnName().equals("depth")) {
                        depthLayout.setVisibility(View.VISIBLE);
                    } else if (field.getColumnName().equals("address")) {
                        addressLayout.setVisibility(View.VISIBLE);
                    } else if (field.getColumnName().equals("postal_code")) {
                        postal_codeLayout.setVisibility(View.VISIBLE);
                    } else if (field.getColumnName().equals("surface")) {
                        surfaceLayout.setVisibility(View.VISIBLE);
                    } else if (field.getColumnName().equals("length")) {
                        lengthLayout.setVisibility(View.VISIBLE);
                    } else if (field.getColumnName().equals("center")) {
                        if (!field.getOptionPojos().isEmpty()) {
                            centerLayout.setVisibility(View.VISIBLE);
                            center_dropdown.setAdapter(new ArrayAdapter<>(getContext(), R.layout.dropdown_menu_popup_item, field.getOptionPojos()));
//                    if (field.getOptionPojos().size() == 1)
                            center_dropdown.setListSelection(0);

                        }
                    } else if (field.getColumnName().equals("edifice")) {
                        edificeLayout.setVisibility(View.VISIBLE);
                    } else if (field.getColumnName().equals("level")) {
                        levelLayout.setVisibility(View.VISIBLE);
                    } else if (field.getColumnName().equals("space")) {
                        spaceLayout.setVisibility(View.VISIBLE);
                    }
                }
                progressDialog.dismiss();
            }
        });

        assetPojo = (AssetPojo) getArguments().getSerializable("assetPojo");
        altaViewModel.getFields(subfamily, settings.getString("access_token", ""));

        brandLayout = view.findViewById(R.id.brandLayout);
        modelLayout = view.findViewById(R.id.modelLayout);
        serieLayout = view.findViewById(R.id.serieLayout);
        characteristicsLayout = view.findViewById(R.id.characteristicsLayout);
        plateLayout = view.findViewById(R.id.plateLayout);
        frame_numberLayout = view.findViewById(R.id.frame_numberLayout);
        widthLayout = view.findViewById(R.id.widthLayout);
        heightLayout = view.findViewById(R.id.heightLayout);
        depthLayout = view.findViewById(R.id.depthLayout);
        dateLayout = view.findViewById(R.id.dateLayout);
        addressLayout = view.findViewById(R.id.addressLayout);
        postal_codeLayout = view.findViewById(R.id.postal_codeLayout);
        surfaceLayout = view.findViewById(R.id.surfaceLayout);
        lengthLayout = view.findViewById(R.id.lengthLayout);
        brand_dropdown = view.findViewById(R.id.brand_dropdown);
        model_dropdown = view.findViewById(R.id.model_dropdown);
        serie_dropdown = view.findViewById(R.id.serie_dropdown);
        characteristics_dropdown = view.findViewById(R.id.characteristics_dropdown);
        plate_dropdown = view.findViewById(R.id.plate_dropdown);
        frame_number_dropdown = view.findViewById(R.id.frame_number_dropdown);
        width_dropdown = view.findViewById(R.id.width_dropdown);
        height_dropdown = view.findViewById(R.id.height_dropdown);
        depth_dropdown = view.findViewById(R.id.depth_dropdown);
        date_dropdown = view.findViewById(R.id.date_dropdown);
        address_dropdown = view.findViewById(R.id.address_dropdown);
        postal_code_dropdown = view.findViewById(R.id.postal_code_dropdown);
        surface_dropdown = view.findViewById(R.id.surface_dropdown);
        length_dropdown = view.findViewById(R.id.length_dropdown);

        centerLayout = view.findViewById(R.id.centerLayout);
        center_dropdown = view.findViewById(R.id.center_dropdown);
        edificeLayout = view.findViewById(R.id.edificeLayout);
        edifice_dropdown = view.findViewById(R.id.edifice_dropdown);
        levelLayout = view.findViewById(R.id.levelLayout);
        level_dropdown = view.findViewById(R.id.level_dropdown);
        spaceLayout = view.findViewById(R.id.spaceLayout);
        space_dropdown = view.findViewById(R.id.space_dropdown);

        realTagLayout = view.findViewById(R.id.realTagLayout);
        virtualTagLayout = view.findViewById(R.id.virtualTagLayout);
        typeTag = "real";

        virtualTagLayout.getEditText().setEnabled(false);

        virtualTagLayout.setEndIconOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (virtualTagLayout.isEndIconCheckable()) {
                    virtualTagLayout.setEndIconCheckable(false);
                    virtualTagLayout.setEndIconDrawable(R.drawable.ic_baseline_check_circle_24);
                    typeTag = "madeup";
                    virtualTagLayout.getEditText().setText(findFirstVirtualTag(fieldPojoArrayList));
                    realTagLayout.getEditText().setText("");
                    realTagLayout.getEditText().setEnabled(false);
                    virtualTagLayout.requestFocus();
                } else {
                    virtualTagLayout.setEndIconCheckable(true);
                    virtualTagLayout.setEndIconDrawable(R.drawable.ic_baseline_radio_button_unchecked_24);
                    typeTag = "real";
                    realTagLayout.requestFocus();
                    realTagLayout.getEditText().setEnabled(true);
                    realTagLayout.getEditText().setText("");
                    virtualTagLayout.getEditText().setText("");
                }
            }
        });

        cal = Calendar.getInstance();

        datePickerDialog = new DatePickerDialog(getContext(), PropertiesFragment.this, cal.get(Calendar.YEAR), cal.get(Calendar.MONTH), cal.get(Calendar.DAY_OF_MONTH));
        center_dropdown.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                OptionPojo optionPojo = (OptionPojo) center_dropdown.getAdapter().getItem(position);
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
                Edifice edifice = (Edifice) edifice_dropdown.getAdapter().getItem(position);
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
                Space space = (Space) space_dropdown.getAdapter().getItem(position);
                assetPojo.setSpace(String.valueOf(space.getId()));
            }
        });

        brand_dropdown.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                OptionPojo optionPojo = (OptionPojo) brand_dropdown.getAdapter().getItem(position);
                assetPojo.setBrand(String.valueOf(optionPojo.getId()));
                model_dropdown.clearListSelection();
                model_dropdown.setText("");
                model_dropdown.setAdapter(new ArrayAdapter<>(getContext(), R.layout.dropdown_menu_popup_item, optionPojo.getAssetModels()));
            }
        });

        model_dropdown.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                AssetModel assetModel = (AssetModel) model_dropdown.getAdapter().getItem(position);
                assetPojo.setAssetModel(String.valueOf(assetModel.getId()));
            }
        });
        dateLayout.setEndIconOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                datePickerDialog.show();
            }
        });


        back_img = view.findViewById(R.id.back_img);
        forward_img = view.findViewById(R.id.forward_img);

        back_img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().onBackPressed();
            }
        });

        forward_img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String tagcode = typeTag.equals("real") ? realTagLayout.getEditText().getText().toString() : virtualTagLayout.getEditText().getText().toString();
                int tagId = findIdTag(fieldPojoArrayList, tagcode, typeTag);
                if (tagId != -1) {
                    Bundle bundle = new Bundle();
                    int subfamily = getArguments().getInt("subfamily");
                    HashMap<String, String> selectedMap = (HashMap<String, String>) getArguments().getSerializable("selectedMap");
                    bundle.putInt("subfamily", subfamily);
                    selectedMap.put("tag", tagcode);
                    assetPojo.setTag(String.valueOf(tagId));
                    assetPojo.setTagType(typeTag.equals("madeup") ? "2" : "3");
                    assetPojo.setAddress(address_dropdown.getText().toString());
                    selectedMap.put("address", address_dropdown.getText().toString());
                    assetPojo.setSubfamily(String.valueOf(subfamily));
                    assetPojo.setSurface(surface_dropdown.getText().toString());
                    selectedMap.put("surface", surface_dropdown.getText().toString());
                    assetPojo.setLength(length_dropdown.getText().toString());
                    selectedMap.put("length", length_dropdown.getText().toString());
                    assetPojo.setCharacteristics(characteristics_dropdown.getText().toString());
                    selectedMap.put("characteristics", characteristics_dropdown.getText().toString());
                    assetPojo.setFrameNumber(frame_number_dropdown.getText().toString());
                    selectedMap.put("frame_number", frame_number_dropdown.getText().toString());
                    assetPojo.setInUseDate(date_dropdown.getText().toString());
                    selectedMap.put("in_use_date", date_dropdown.getText().toString());
                    assetPojo.setSerialNumber(serie_dropdown.getText().toString());
                    selectedMap.put("serie", serie_dropdown.getText().toString());
                    assetPojo.setPlate(plate_dropdown.getText().toString());
                    selectedMap.put("plate", plate_dropdown.getText().toString());
                    assetPojo.setPostalCode(postal_code_dropdown.getText().toString());
                    selectedMap.put("postal_code", postal_code_dropdown.getText().toString());
                    selectedMap.put("center", center_dropdown.getText().toString());
                    selectedMap.put("edifice", edifice_dropdown.getText().toString());
                    selectedMap.put("level", level_dropdown.getText().toString());
                    selectedMap.put("space", space_dropdown.getText().toString());
                    selectedMap.put("brand", brand_dropdown.getText().toString());
                    selectedMap.put("model", model_dropdown.getText().toString());

                    String measures = (!width_dropdown.getText().toString().equals("") && !height_dropdown.getText().toString().equals("") && !depth_dropdown.getText().toString().equals("")) ? width_dropdown.getText().toString() + "x" + height_dropdown.getText().toString() + "x" + depth_dropdown.getText().toString() : "";
                    assetPojo.setMeasures(measures);
                    selectedMap.put("measures", measures);
                    bundle.putSerializable("fieldPojos", fieldPojoArrayList);
                    bundle.putSerializable("assetPojo", assetPojo);
                    bundle.putSerializable("selectedMap", selectedMap);
                    Navigation.findNavController(v).navigate(R.id.action_nav_properties_to_nav_description, bundle);
                } else {

                }
            }
        });
    }

    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
        date_dropdown.setText(dayOfMonth + "/" + month + "/" + year);
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
