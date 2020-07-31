package com.coresolutions.coreinvent.ui.movement;

import android.app.ProgressDialog;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.navigation.Navigation;

import com.coresolutions.coreinvent.R;
import com.coresolutions.coreinvent.data.pojos.Center;
import com.coresolutions.coreinvent.data.pojos.Edifice;
import com.coresolutions.coreinvent.data.pojos.FindAssetPojo;
import com.coresolutions.coreinvent.data.pojos.Level;
import com.coresolutions.coreinvent.data.pojos.OptionPojo;
import com.coresolutions.coreinvent.data.pojos.Space;
import com.coresolutions.coreinvent.ui.alta.AltaViewModel;
import com.google.android.material.textfield.TextInputLayout;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class MovementTagFragment extends Fragment {
    private ImageView back_img;
    private ImageView forward_img;
    private AltaViewModel altaViewModel;
    private SharedPreferences settings;
    private ProgressDialog progressDialog;
    private TextInputLayout realTag;
    private String token;



    public MovementTagFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_movement_tag, container, false);
        return view;
    }


    @Override
    public void onViewCreated(@NonNull final View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        back_img = view.findViewById(R.id.back_img);
        forward_img = view.findViewById(R.id.forward_img);
        realTag = view.findViewById(R.id.realTag);

        settings = PreferenceManager.getDefaultSharedPreferences(getContext());
        altaViewModel = ViewModelProviders.of(this).get(AltaViewModel.class);
        token = settings.getString("access_token", "");
        progressDialog = new ProgressDialog(getContext());
        progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progressDialog.setMessage("Cargando datos...");
        progressDialog.setCancelable(false);

        back_img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().onBackPressed();
            }
        });

        forward_img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String tag = realTag.getEditText().getText().toString();
                if (!tag.equals("")) {
                    altaViewModel.getAssetByTag(token, tag);
                } else {
                    Toast.makeText(getContext(), "Debe introducir una etiqueta para continuar.", Toast.LENGTH_LONG).show();
                }
            }
        });

        altaViewModel.getAssetResult().observe(this, new Observer<FindAssetPojo>() {
            @Override
            public void onChanged(FindAssetPojo findAssetPojo) {
                if (findAssetPojo != null) {
                    Bundle bundle = new Bundle();
                    bundle.putSerializable("asset", findAssetPojo);
                    Navigation.findNavController(view).navigate(R.id.action_movement_tag_fragment_to_movement_location_fragment, bundle);
                } else {
                    Toast.makeText(getContext(), "No existe ningún bien en inventario con esa etiqueta", Toast.LENGTH_LONG).show();
                }
            }
        });



    }
}
