package com.coresolutions.coreinvent.ui.main;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.AsyncTask;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.preference.PreferenceManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.coresolutions.coreinvent.R;
import com.coresolutions.coreinvent.data.Constants;
import com.coresolutions.coreinvent.data.pojos.AssetPojo;
import com.coresolutions.coreinvent.data.pojos.FieldPojo;
import com.coresolutions.coreinvent.data.pojos.FindAssetPojo;
import com.coresolutions.coreinvent.ui.alta.AltaViewModel;
import com.coresolutions.coreinvent.ui.baja.BajaActivity;
import com.coresolutions.coreinvent.ui.login.LoginActivity;
import com.coresolutions.coreinvent.ui.movement.MovementActivity;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link DetailFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class DetailFragment extends DialogFragment {


    private ImageView close;
    private ImageView asset_img;
    private SharedPreferences settings;
    private AltaViewModel altaViewModel;
    private FindAssetPojo asset;
    private ConstraintLayout loading;

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


    private LinearLayout baja_layout;
    private LinearLayout movement_layout;

    private ArrayList<FieldPojo> fieldPojoArrayList;


    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "id";
    private static final String ARG_PARAM2 = "token";

    // TODO: Rename and change types of parameters
    private int id;
    private String token;

    public DetailFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param id    Parameter 1.
     * @param token Parameter 2.
     * @return A new instance of fragment DetailFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static DetailFragment newInstance(int id, String token) {
        DetailFragment fragment = new DetailFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_PARAM1, id);
        args.putString(ARG_PARAM2, token);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setCancelable(false);
        if (getArguments() != null) {
            id = getArguments().getInt(ARG_PARAM1);
            token = getArguments().getString(ARG_PARAM2);
        }
        setStyle(STYLE_NO_TITLE, R.style.DialogTheme_transparent);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_detail, container, false);
        if (getDialog() != null && getDialog().getWindow() != null) {
            getDialog().getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
            getDialog().getWindow().requestFeature(Window.FEATURE_NO_TITLE);
        }
        return view;
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        settings = PreferenceManager.getDefaultSharedPreferences(getContext());
        altaViewModel = ViewModelProviders.of(this).get(AltaViewModel.class);

        loading = view.findViewById(R.id.loading);
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
        close = view.findViewById(R.id.close_img);


        baja_layout = view.findViewById(R.id.baja_layout);
        movement_layout = view.findViewById(R.id.movement_layout);

//        Bitmap bitmap = BitmapFactory.decodeFile(assetPojo.getImage().getAbsolutePath());
//        asset_img.setImageBitmap(bitmap);

        altaViewModel.getAssetResult().observe(this, new Observer<FindAssetPojo>() {
            @Override
            public void onChanged(FindAssetPojo findAssetPojo) {
                asset = findAssetPojo;
                altaViewModel.getFields(asset.getType().getSubFamilyId(), settings.getString("access_token", ""));
                new DownloadImageTask(asset_img)
                        .execute(Constants.SERVER_URL + asset.getUrl_photo());
                if (asset.getTag() != null) {
                    tag_code.setText(asset.getTag().getCode());
                    tag_code.setAlpha(1);
                }
                if (!asset.getType().getName().equals("")) {
                    type_layout.setVisibility(View.VISIBLE);
                    type.setText(asset.getType().getName());
                    type.setAlpha(1);
                }
                if (asset.getAssetModel() != null) {
                    if (asset.getAssetModel().getBrand() != null) {
                        brand_layout.setVisibility(View.VISIBLE);
                        brand.setText(asset.getAssetModel().getBrand().getName());
                        brand.setAlpha(1);
                    }
                }
                if (asset.getAssetModel() != null) {
                    brand_layout.setVisibility(View.VISIBLE);
                    model.setText(asset.getAssetModel().getName());
                    model.setAlpha(1);
                }
                if (asset.getLocalization() != null) {
                    location_layout.setVisibility(View.VISIBLE);
//                    location2_layout.setVisibility(View.VISIBLE);
                    center.setText(asset.getLocalization());
                    center.setAlpha(1);
                }
//                        if (entry.getKey().equals("edifice")) {
//                            location_layout.setVisibility(View.VISIBLE);
//                            location2_layout.setVisibility(View.VISIBLE);
//                            edifice.setText(entry.getValue());
//                            edifice.setAlpha(1);
//                        }
//                        if (entry.getKey().equals("level")) {
//                            location_layout.setVisibility(View.VISIBLE);
//                            location2_layout.setVisibility(View.VISIBLE);
//                            level.setText(entry.getValue());
//                            level.setAlpha(1);
//                        }
//                        if (entry.getKey().equals("space")) {
//                            location_layout.setVisibility(View.VISIBLE);
//                            location2_layout.setVisibility(View.VISIBLE);
//                            space.setText(entry.getValue());
//                            space.setAlpha(1);
//                        }
                if (asset.getCharacteristics() != null) {
                    characteristics_layout.setVisibility(View.VISIBLE);
                    if (asset.getCharacteristics().getImportado() != null) {
                        characteristics.setText(asset.getCharacteristics().getImportado());
                        characteristics.setAlpha(1);
                    }
                }
                if (asset.getSerialNumber() != null) {
                    serie_layout.setVisibility(View.VISIBLE);
                    if (!asset.getSerialNumber().equals("")) {
                        serie.setText(asset.getSerialNumber());
                        serie.setAlpha(1);
                    }
                }


                if (asset.getWidth() != null && asset.getHeight() != null && asset.getDepth() != null) {
                    String measures1 = (!asset.getWidth().toString().equals("") && !asset.getHeight().toString().equals("") && !asset.getDepth().toString().equals("")) ? asset.getWidth().toString() + "x" + asset.getHeight().toString() + "x" + asset.getDepth().toString() : "";
                    measures_layout.setVisibility(View.VISIBLE);
                    measures.setText(measures1);
                    measures.setAlpha(1);
                }
                if (asset.getFrameNumber() != null) {
                    frameNumber_layout.setVisibility(View.VISIBLE);
                    frameNumber.setText(asset.getFrameNumber().toString());
                    frameNumber.setAlpha(1);
                }
                if (asset.getPostalCode() != null) {
                    postalCode_layout.setVisibility(View.VISIBLE);
                    postalCode.setText(asset.getPostalCode().toString());
                    postalCode.setAlpha(1);
                }
                if (asset.getAddress() != null) {
                    address_layout.setVisibility(View.VISIBLE);
                    address.setText(asset.getAddress().toString());
                    address.setAlpha(1);
                }
                if (asset.getSurface() != null) {
                    surface_layout.setVisibility(View.VISIBLE);
                    surface.setText(asset.getSurface().toString());
                    surface.setAlpha(1);
                }
                if (asset.getLength() != null) {
                    length_layout.setVisibility(View.VISIBLE);
                    length.setText(asset.getLength().toString());
                    length.setAlpha(1);
                }
                if (asset.getEconomicAspect() != null) {
                    inUseDate_layout.setVisibility(View.VISIBLE);
                    inUseDate.setText(asset.getEconomicAspect().getInUseDate().toString());
                    inUseDate.setAlpha(1);
                }
                if (asset.getObservations() != null) {
                    description_layout.setVisibility(View.VISIBLE);
                    description.setText(asset.getObservations());
                    description.setAlpha(1);
                }
            }

        });

        altaViewModel.getRegisterResult().observe(this, new Observer<List<FieldPojo>>() {
            @Override
            public void onChanged(List<FieldPojo> fieldPojos) {
                fieldPojoArrayList = new ArrayList<FieldPojo>(fieldPojos);
                for (FieldPojo field : fieldPojoArrayList) {

//            if (field.getColumnName().equals("brand") && !field.getOptionPojos().isEmpty()) {
                    if (field.getColumnName().equals("brand")) {
                        brand_layout.setVisibility(View.VISIBLE);
                    } else if (field.getColumnName().equals("serial_number")) {
                        serie_layout.setVisibility(View.VISIBLE);
                    } else if (field.getColumnName().equals("characteristics")) {
                        characteristics_layout.setVisibility(View.VISIBLE);
                    } else if (field.getColumnName().equals("plate")) {
                        plate_layout.setVisibility(View.VISIBLE);
                    } else if (field.getColumnName().equals("frame_number")) {
                        frameNumber_layout.setVisibility(View.VISIBLE);
                    } else if (field.getColumnName().equals("width")) {
                        measures_layout.setVisibility(View.VISIBLE);
                    } else if (field.getColumnName().equals("height")) {
                        measures_layout.setVisibility(View.VISIBLE);
                    } else if (field.getColumnName().equals("depth")) {
                        measures_layout.setVisibility(View.VISIBLE);
                    } else if (field.getColumnName().equals("address")) {
                        address_layout.setVisibility(View.VISIBLE);
                    } else if (field.getColumnName().equals("postal_code")) {
                        postalCode_layout.setVisibility(View.VISIBLE);
                    } else if (field.getColumnName().equals("surface")) {
                        surface_layout.setVisibility(View.VISIBLE);
                    } else if (field.getColumnName().equals("length")) {
                        length_layout.setVisibility(View.VISIBLE);
                    } else if (field.getColumnName().equals("center")) {
                        if (!field.getOptionPojos().isEmpty()) {
                            location_layout.setVisibility(View.VISIBLE);

                        }
                    } else if (field.getColumnName().equals("edifice")) {
                        location_layout.setVisibility(View.VISIBLE);
                    } else if (field.getColumnName().equals("level")) {
                        location_layout.setVisibility(View.VISIBLE);
                    } else if (field.getColumnName().equals("space")) {
                        location_layout.setVisibility(View.VISIBLE);
                    }
                }
            }
        });

        altaViewModel.getAssetById(id, token);



        close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });


        baja_layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
                Intent i = new Intent(getContext(), BajaActivity.class);
                i.putExtra("asset", asset);
                startActivity(i);
            }
        });

        movement_layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
                Intent i = new Intent(getContext(), MovementActivity.class);
                i.putExtra("asset", asset);
                startActivity(i);
            }
        });


    }

    private class DownloadImageTask extends AsyncTask<String, Void, Bitmap> {
        ImageView bmImage;

        public DownloadImageTask(ImageView bmImage) {
            this.bmImage = bmImage;
        }

        protected Bitmap doInBackground(String... urls) {
            String urldisplay = urls[0];
            Bitmap mIcon11 = null;
            try {
                InputStream in = new java.net.URL(urldisplay).openStream();
                mIcon11 = BitmapFactory.decodeStream(in);
            } catch (Exception e) {
                Log.e("Error", e.getMessage());
                e.printStackTrace();
            }
            return mIcon11;
        }

        protected void onPostExecute(Bitmap result) {
            bmImage.setImageBitmap(result);
            loading.setVisibility(View.GONE);
        }
    }

}

