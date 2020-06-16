package com.coresolutions.coreinvent.ui.alta;

import android.app.DatePickerDialog;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.DatePicker;
import android.widget.ImageView;

import com.coresolutions.coreinvent.R;
import com.coresolutions.coreinvent.ui.alta.pojos.AssetModelPojo;
import com.coresolutions.coreinvent.ui.alta.pojos.AssetPojo;
import com.coresolutions.coreinvent.ui.alta.pojos.FieldPojo;
import com.coresolutions.coreinvent.ui.alta.pojos.OptionPojo;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;

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
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        fieldPojoArrayList = (ArrayList<FieldPojo>) getArguments().getSerializable("fieldPojos");
        assetPojo = (AssetPojo) getArguments().getSerializable("assetPojo");

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

        cal = Calendar.getInstance();

        datePickerDialog = new DatePickerDialog(getContext(), PropertiesFragment.this, cal.get(Calendar.YEAR), cal.get(Calendar.MONTH), cal.get(Calendar.DAY_OF_MONTH));

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
            }
        }

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
                AssetModelPojo assetModelPojo = (AssetModelPojo) model_dropdown.getAdapter().getItem(position);
                assetPojo.setAssetModel(String.valueOf(assetModelPojo.getId()));
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
                Bundle bundle = new Bundle();
                bundle.putInt("subfamily", getArguments().getInt("subfamily"));
                HashMap<Integer, String> selectedMap = (HashMap<Integer, String>) getArguments().getSerializable("selectedMap");
                bundle.putString("from", "properties");
                bundle.putSerializable("fieldPojos", fieldPojoArrayList);
                bundle.putSerializable("assetPojo", assetPojo);
                bundle.putSerializable("selectedMap", selectedMap);
                Navigation.findNavController(v).navigate(R.id.action_nav_properties_to_nav_location, bundle);
            }
        });

        forward_img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle = new Bundle();
                int subfamily = getArguments().getInt("subfamily");
                HashMap<Integer, String> selectedMap = (HashMap<Integer, String>) getArguments().getSerializable("selectedMap");
                bundle.putInt("subfamily", subfamily);
                assetPojo.setAddress(address_dropdown.getText().toString());
                selectedMap.put(R.string.address, address_dropdown.getText().toString());
                assetPojo.setSubfamily(String.valueOf(subfamily));
                assetPojo.setArea(surface_dropdown.getText().toString());
                selectedMap.put(R.string.surface, surface_dropdown.getText().toString());
                assetPojo.setLength(length_dropdown.getText().toString());
                selectedMap.put(R.string.length, length_dropdown.getText().toString());
                assetPojo.setCharacteristics(characteristics_dropdown.getText().toString());
                selectedMap.put(R.string.characteristics, characteristics_dropdown.getText().toString());
                assetPojo.setFrameNumber(frame_number_dropdown.getText().toString());
                selectedMap.put(R.string.frame_number, frame_number_dropdown.getText().toString());
                assetPojo.setInUseDate(date_dropdown.getText().toString());
                selectedMap.put(R.string.in_use_date, date_dropdown.getText().toString());
                assetPojo.setSerialNumber(serie_dropdown.getText().toString());
                selectedMap.put(R.string.serie, serie_dropdown.getText().toString());
                assetPojo.setPlate(plate_dropdown.getText().toString());
                selectedMap.put(R.string.plate, plate_dropdown.getText().toString());
                assetPojo.setPostalCode(postal_code_dropdown.getText().toString());
                selectedMap.put(R.string.postal_code, postal_code_dropdown.getText().toString());
                String measures = (!width_dropdown.getText().toString().equals("") && !height_dropdown.getText().toString().equals("") && !depth_dropdown.getText().toString().equals("")) ? width_dropdown.getText().toString() + "x" + height_dropdown.getText().toString() + "x" + depth_dropdown.getText().toString() : "";
                assetPojo.setMeasures(measures);
                selectedMap.put(R.string.measures, measures);
                bundle.putSerializable("fieldPojos", fieldPojoArrayList);
                bundle.putSerializable("assetPojo", assetPojo);
                bundle.putSerializable("selectedMap", selectedMap);
                Navigation.findNavController(v).navigate(R.id.action_nav_properties_to_nav_description, bundle);
            }
        });
    }

    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
        date_dropdown.setText(year + "/" + month + "/" + dayOfMonth);
    }
}
