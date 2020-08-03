package com.coresolutions.coreinvent.ui.alta;

import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
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
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.coresolutions.coreinvent.R;
import com.coresolutions.coreinvent.data.pojos.AssetPojo;
import com.coresolutions.coreinvent.data.pojos.FieldPojo;
import com.coresolutions.coreinvent.ui.movement.MovementLocationFragment;
import com.coresolutions.coreinvent.ui.movement.NotificationFragment;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * A simple {@link Fragment} subclass.
 */
public class ConfimationFragment extends Fragment implements NotificationFragment.OnClickListener, NotificationFragment.OnCloseListener {

    private ImageView back_img;
    private ImageView forward_img;
    private ImageView asset_img;
    private SharedPreferences settings;
    private AltaViewModel altaViewModel;
    private AssetPojo assetPojo;
    private ArrayList<FieldPojo> fieldPojoArrayList;
    private HashMap<String, String> selectedMap;
    private TextView tag_code;

    private LinearLayout type_layout;
    private TextView type;

    private LinearLayout brand_layout;
    private TextView brand;
    private TextView model;

    private LinearLayout location_layout;
    private LinearLayout location2_layout;
    private TextView center;
    private TextView edifice;
    private TextView level;
    private TextView space;

    private LinearLayout characteristics_layout;
    private TextView characteristics;

    private LinearLayout serie_layout;
    private TextView serie;

    private LinearLayout measures_layout;
    private TextView measures;

    private LinearLayout frameNumber_layout;
    private TextView frameNumber;

    private LinearLayout plate_layout;
    private TextView plate;

    private LinearLayout postalCode_layout;
    private TextView postalCode;

    private LinearLayout address_layout;
    private TextView address;

    private LinearLayout surface_layout;
    private TextView surface;

    private LinearLayout length_layout;
    private TextView length;

    private LinearLayout area_layout;
    private TextView area;

    private LinearLayout inUseDate_layout;
    private TextView inUseDate;

    private LinearLayout description_layout;
    private TextView description;

    private CheckBox notificationCheckBox;
    private String token;

    public ConfimationFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_confimation, container, false);
    }


    @Override
    public void onViewCreated(@NonNull final View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        settings = PreferenceManager.getDefaultSharedPreferences(getContext());
        altaViewModel = ViewModelProviders.of(this).get(AltaViewModel.class);
        selectedMap = (HashMap<String, String>) getArguments().getSerializable("selectedMap");
        fieldPojoArrayList = (ArrayList<FieldPojo>) getArguments().getSerializable("fieldPojos");
        assetPojo = (AssetPojo) getArguments().getSerializable("assetPojo");
        token = settings.getString("access_token", "");

        notificationCheckBox = view.findViewById(R.id.notificationCheckBox);


        tag_code = view.findViewById(R.id.tag_code);
        type_layout = view.findViewById(R.id.type_layout);
        type = view.findViewById(R.id.type);

        brand_layout = view.findViewById(R.id.brand_layout);
        brand = view.findViewById(R.id.brand);
        model = view.findViewById(R.id.model);

        location_layout = view.findViewById(R.id.location_layout);
        location2_layout = view.findViewById(R.id.location2_layout);
        center = view.findViewById(R.id.center);
        edifice = view.findViewById(R.id.edifice);
        level = view.findViewById(R.id.level);
        space = view.findViewById(R.id.space);

        characteristics_layout = view.findViewById(R.id.characteristics_layout);
        characteristics = view.findViewById(R.id.characteristics);

        serie_layout = view.findViewById(R.id.serie_layout);
        serie = view.findViewById(R.id.serie);

        measures_layout = view.findViewById(R.id.measures_layout);
        measures = view.findViewById(R.id.measures);

        frameNumber_layout = view.findViewById(R.id.frameNumber_layout);
        frameNumber = view.findViewById(R.id.frameNumber);

        plate_layout = view.findViewById(R.id.plate_layout);
        plate = view.findViewById(R.id.plate);

        postalCode_layout = view.findViewById(R.id.postalCode_layout);
        postalCode = view.findViewById(R.id.postalCode);

        address_layout = view.findViewById(R.id.address_layout);
        address = view.findViewById(R.id.address);

        surface_layout = view.findViewById(R.id.surface_layout);
        surface = view.findViewById(R.id.surface);

        length_layout = view.findViewById(R.id.length_layout);
        length = view.findViewById(R.id.length);

        area_layout = view.findViewById(R.id.area_layout);
        area = view.findViewById(R.id.area);

        inUseDate_layout = view.findViewById(R.id.inUseDate_layout);
        inUseDate = view.findViewById(R.id.inUseDate);

        description_layout = view.findViewById(R.id.description_layout);
        description = view.findViewById(R.id.description);
        asset_img = view.findViewById(R.id.asset_img);


        notificationCheckBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    NotificationFragment notificationFragment = new NotificationFragment(token, ConfimationFragment.this, ConfimationFragment.this);
                    notificationFragment.show(getFragmentManager(), "notification");
                } else {
                    assetPojo.clearNotification();
                }
            }
        });


        if (assetPojo.getImage() != null) {
            Bitmap bitmap = BitmapFactory.decodeFile(assetPojo.getImage().getAbsolutePath());
            asset_img.setImageBitmap(bitmap);
        }

        for (Map.Entry<String, String> entry : selectedMap.entrySet()) {
            if (!entry.getValue().equals("")) {
                if (entry.getKey().equals("tag")) {
                    tag_code.setText(entry.getValue());
                    tag_code.setAlpha(1);
                } else if (entry.getKey().equals("type")) {
                    type_layout.setVisibility(View.VISIBLE);
                    type.setText(entry.getValue());
                    type.setAlpha(1);
                } else if (entry.getKey().equals("brand")) {
                    brand_layout.setVisibility(View.VISIBLE);
                    brand.setText(entry.getValue());
                    brand.setAlpha(1);
                } else if (entry.getKey().equals("model")) {
                    brand_layout.setVisibility(View.VISIBLE);
                    model.setText(entry.getValue());
                    model.setAlpha(1);
                } else if (entry.getKey().equals("center")) {
                    location_layout.setVisibility(View.VISIBLE);
                    location2_layout.setVisibility(View.VISIBLE);
                    center.setText(entry.getValue());
                    center.setAlpha(1);
                } else if (entry.getKey().equals("edifice")) {
                    location_layout.setVisibility(View.VISIBLE);
                    location2_layout.setVisibility(View.VISIBLE);
                    edifice.setText(entry.getValue());
                    edifice.setAlpha(1);
                } else if (entry.getKey().equals("level")) {
                    location_layout.setVisibility(View.VISIBLE);
                    location2_layout.setVisibility(View.VISIBLE);
                    level.setText(entry.getValue());
                    level.setAlpha(1);
                } else if (entry.getKey().equals("space")) {
                    location_layout.setVisibility(View.VISIBLE);
                    location2_layout.setVisibility(View.VISIBLE);
                    space.setText(entry.getValue());
                    space.setAlpha(1);
                } else if (entry.getKey().equals("characteristics")) {
                    characteristics_layout.setVisibility(View.VISIBLE);
                    characteristics.setText(entry.getValue());
                } else if (entry.getKey().equals("serie")) {
                    serie_layout.setVisibility(View.VISIBLE);
                    serie.setText(entry.getValue());
                    serie.setAlpha(1);
                } else if (entry.getKey().equals("measures")) {
                    measures_layout.setVisibility(View.VISIBLE);
                    measures.setText(entry.getValue());
                    measures.setAlpha(1);
                } else if (entry.getKey().equals("frame_number")) {
                    frameNumber_layout.setVisibility(View.VISIBLE);
                    frameNumber.setText(entry.getValue());
                    frameNumber.setAlpha(1);
                } else if (entry.getKey().equals("postal_code")) {
                    postalCode_layout.setVisibility(View.VISIBLE);
                    postalCode.setText(entry.getValue());
                    postalCode.setAlpha(1);
                } else if (entry.getKey().equals("address")) {
                    address_layout.setVisibility(View.VISIBLE);
                    address.setText(entry.getValue());
                    address.setAlpha(1);
                } else if (entry.getKey().equals("surface")) {
                    surface_layout.setVisibility(View.VISIBLE);
                    surface.setText(entry.getValue());
                    surface.setAlpha(1);
                } else if (entry.getKey().equals("length")) {
                    length_layout.setVisibility(View.VISIBLE);
                    length.setText(entry.getValue());
                    length.setAlpha(1);
                } else if (entry.getKey().equals("in_use_date")) {
                    inUseDate_layout.setVisibility(View.VISIBLE);
                    inUseDate.setText(entry.getValue());
                    inUseDate.setAlpha(1);
                } else if (entry.getKey().equals("description")) {
                    description_layout.setVisibility(View.VISIBLE);
                    description.setText(entry.getValue());
                    description.setAlpha(1);
                }
            }
        }


        altaViewModel.getSubscriptionResult().observe(this, new Observer<Integer>() {
            @Override
            public void onChanged(Integer code) {
                Bundle bundle = new Bundle();
                if (code == 200) {
                    bundle.putString("result", "Registro de alta guardado con éxito");
                } else {
                    bundle.putString("result", "Ha ocurrido un error al registrar el alta");
                }

                Navigation.findNavController(view).navigate(R.id.action_nav_confimation_to_nav_result, bundle);
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
                altaViewModel.assetSubscription(settings.getString("access_token", ""), assetPojo);
            }
        });
    }

    @Override
    public void OnClickListener(int user_id, String description) {
        assetPojo.addNotifyUser(user_id);
        assetPojo.setNotifyText(description);

    }

    @Override
    public void OnCloseListener() {
        notificationCheckBox.setChecked(false);
    }
}
