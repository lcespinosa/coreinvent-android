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
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.coresolutions.coreinvent.R;
import com.coresolutions.coreinvent.ui.alta.pojos.AssetPojo;
import com.coresolutions.coreinvent.ui.alta.pojos.FieldPojo;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * A simple {@link Fragment} subclass.
 */
public class ConfimationFragment extends Fragment {

    private ImageView back_img;
    private ImageView forward_img;
    private TextView info;
    private SharedPreferences settings;
    private AltaViewModel altaViewModel;
    private AssetPojo assetPojo;
    private ArrayList<FieldPojo> fieldPojoArrayList;
    private LinearLayout layout;
    private HashMap<Integer, String> selectedMap;

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
        selectedMap = (HashMap<Integer, String>) getArguments().getSerializable("selectedMap");
        fieldPojoArrayList = (ArrayList<FieldPojo>) getArguments().getSerializable("fieldPojos");
        assetPojo = (AssetPojo) getArguments().getSerializable("assetPojo");
        layout = view.findViewById(R.id.layout);


        LayoutInflater inflater = this.getLayoutInflater();
        for (Map.Entry<Integer, String> entry : selectedMap.entrySet()) {
            View inflatedView = inflater.inflate(R.layout.properties_item, null);

            TextView property_name = inflatedView.findViewById(R.id.property_name);
            TextView property_value = inflatedView.findViewById(R.id.property_value);
            property_name.setText(entry.getKey());
            property_value.setText(entry.getValue());
            layout.addView(inflatedView);

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
        info = view.findViewById(R.id.info);
        forward_img = view.findViewById(R.id.forward_img);
        Gson gson = new Gson();
        info.setText(gson.toJson(assetPojo));

        back_img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle = new Bundle();
                bundle.putInt("subfamily", getArguments().getInt("subfamily"));

                bundle.putString("from", "properties");
                bundle.putSerializable("fieldPojos", fieldPojoArrayList);
                bundle.putSerializable("assetPojo", assetPojo);
                bundle.putSerializable("selectedMap", selectedMap);
                Navigation.findNavController(v).navigate(R.id.action_nav_confimation_to_nav_description, bundle);
            }
        });

        forward_img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                altaViewModel.assetSubscription(settings.getString("access_token", ""), assetPojo);
            }
        });
    }

}
