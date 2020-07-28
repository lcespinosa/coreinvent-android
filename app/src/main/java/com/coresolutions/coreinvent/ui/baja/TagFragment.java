package com.coresolutions.coreinvent.ui.baja;

import android.app.ProgressDialog;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.navigation.Navigation;

import com.coresolutions.coreinvent.R;
import com.coresolutions.coreinvent.data.pojos.Unsubscription;
import com.coresolutions.coreinvent.ui.alta.AltaViewModel;
import com.google.android.material.textfield.TextInputLayout;

/**
 * A simple {@link Fragment} subclass.
 */
public class TagFragment extends Fragment {
    private ImageView back_img;
    private ImageView forward_img;
    private AutoCompleteTextView madeupTag;
    private AltaViewModel altaViewModel;
    private SharedPreferences settings;
    private ProgressDialog progressDialog;

    public TagFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_tag, container, false);
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        settings = PreferenceManager.getDefaultSharedPreferences(getContext());
        altaViewModel = ViewModelProviders.of(this).get(AltaViewModel.class);

        progressDialog = new ProgressDialog(getContext());
        progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progressDialog.setMessage("Cargando datos...");
        progressDialog.setCancelable(false);
        progressDialog.show();

        madeupTag = view.findViewById(R.id.madeupTag);


        altaViewModel.getUnsubscriptionResult().observe(this, new Observer<Unsubscription>() {
            @Override
            public void onChanged(Unsubscription unsubscription) {
                madeupTag.setAdapter(new ArrayAdapter<>(getContext(), R.layout.dropdown_menu_popup_item, unsubscription.getMadeupTags()));
                progressDialog.dismiss();
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

        altaViewModel.getUnsubscriptionData(settings.getString("access_token", ""));
        forward_img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Navigation.findNavController(v).navigate(R.id.action_tag_fragment_to_reason_fragment);
            }
        });
    }
}
