package com.coresolutions.coreinvent.ui.movement;

import android.app.ProgressDialog;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.navigation.Navigation;

import com.coresolutions.coreinvent.R;
import com.coresolutions.coreinvent.data.Constants;
import com.coresolutions.coreinvent.data.pojos.Center;
import com.coresolutions.coreinvent.data.pojos.Edifice;
import com.coresolutions.coreinvent.data.pojos.FindAssetPojo;
import com.coresolutions.coreinvent.data.pojos.Level;
import com.coresolutions.coreinvent.data.pojos.Space;
import com.coresolutions.coreinvent.ui.alta.AltaViewModel;
import com.coresolutions.coreinvent.ui.baja.BajaReasonFragment;

import java.io.InputStream;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class MovementLocationFragment extends Fragment {

    private FindAssetPojo asset;
    private ImageView asset_img;
    private ImageView back_img;
    private ImageView forward_img;
    private TextView asset_text;
    private AltaViewModel altaViewModel;
    private SharedPreferences settings;
    private ProgressDialog progressDialog;

    private AutoCompleteTextView center_dropdown;
    private AutoCompleteTextView edifice_dropdown;
    private AutoCompleteTextView level_dropdown;
    private AutoCompleteTextView space_dropdown;

    private String token;

    public MovementLocationFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_movement_location, container, false);
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        settings = PreferenceManager.getDefaultSharedPreferences(getContext());
        altaViewModel = ViewModelProviders.of(this).get(AltaViewModel.class);
        asset_img = view.findViewById(R.id.asset_img);
        asset_text = view.findViewById(R.id.asset_text);

        back_img = view.findViewById(R.id.back_img);
        forward_img = view.findViewById(R.id.forward_img);
        token = settings.getString("access_token", "");

        center_dropdown = view.findViewById(R.id.center_dropdown);
        edifice_dropdown = view.findViewById(R.id.edifice_dropdown);
        level_dropdown = view.findViewById(R.id.level_dropdown);
        space_dropdown = view.findViewById(R.id.space_dropdown);

        progressDialog = new ProgressDialog(getContext());
        progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progressDialog.setMessage("Cargando datos...");
        progressDialog.setCancelable(false);
        progressDialog.show();


        if (getArguments().getSerializable("asset") != null) {
            asset = (FindAssetPojo) getArguments().getSerializable("asset");
        }

        if (asset != null) {
            new MovementLocationFragment.DownloadImageTask(asset_img)
                    .execute(Constants.SERVER_URL + asset.getUrl_photo());
            asset_text.setText(asset.getType().getName());
        }

        back_img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().onBackPressed();
            }
        });

        forward_img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                
            }
        });


        center_dropdown.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Center center = (Center) center_dropdown.getAdapter().getItem(position);
//                assetPojo.setCenter(String.valueOf(optionPojo.getId()));
                edifice_dropdown.clearListSelection();
                edifice_dropdown.setText("");
                level_dropdown.clearListSelection();
                level_dropdown.setText("");
                space_dropdown.clearListSelection();
                space_dropdown.setText("");
                edifice_dropdown.setAdapter(new ArrayAdapter<>(getContext(), R.layout.dropdown_menu_popup_item, center.getEdifices()));
            }
        });

        edifice_dropdown.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Edifice edifice = (Edifice) edifice_dropdown.getAdapter().getItem(position);
//                assetPojo.setEdificeId(String.valueOf(edifice.getId()));
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
//                assetPojo.setSpace(String.valueOf(space.getId()));
            }
        });


        altaViewModel.getCenterResult().observe(this, new Observer<List<Center>>() {
            @Override
            public void onChanged(List<Center> centers) {
                center_dropdown.setAdapter(new ArrayAdapter<>(getContext(), R.layout.dropdown_menu_popup_item, centers));
            }
        });

        altaViewModel.getCenters(token);

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
            progressDialog.dismiss();
//            loading.setVisibility(View.GONE);
        }
    }
}
