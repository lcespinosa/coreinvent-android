package com.coresolutions.coreinvent.ui.baja;

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
import com.coresolutions.coreinvent.data.pojos.AssetPojo;
import com.coresolutions.coreinvent.data.pojos.FindAssetPojo;
import com.coresolutions.coreinvent.data.pojos.Unsubscription;
import com.coresolutions.coreinvent.data.pojos.UnsubscriptionVar;
import com.coresolutions.coreinvent.ui.alta.AltaViewModel;
import com.coresolutions.coreinvent.ui.main.DetailFragment;

import java.io.InputStream;

/**
 * A simple {@link Fragment} subclass.
 */
public class BajaReasonFragment extends Fragment {

    private FindAssetPojo asset;
    private UnsubscriptionVar unsubscriptionVar;
    private ImageView asset_img;
    private TextView asset_text;
    private AutoCompleteTextView baja_reason;
    private AltaViewModel altaViewModel;
    private SharedPreferences settings;
    private ProgressDialog progressDialog;

    public BajaReasonFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_baja_reason, container, false);
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

        asset_img = view.findViewById(R.id.asset_img);
        asset_text = view.findViewById(R.id.asset_text);
        baja_reason = view.findViewById(R.id.baja_reason);

        altaViewModel.getUnsubscriptionResult().observe(this, new Observer<Unsubscription>() {
            @Override
            public void onChanged(Unsubscription unsubscription) {
                baja_reason.setAdapter(new ArrayAdapter<>(getContext(), R.layout.dropdown_menu_popup_item, unsubscription.getUnsubscriptionVars()));
                progressDialog.dismiss();
            }
        });


        if (getArguments().getSerializable("asset") != null) {
            asset = (FindAssetPojo) getArguments().getSerializable("asset");
        }

        if (getArguments().getSerializable("unsubscriptionVar") != null) {
            unsubscriptionVar = (UnsubscriptionVar) getArguments().getSerializable("unsubscriptionVar");
        } else {
            altaViewModel.getUnsubscriptionData(settings.getString("access_token", ""));
        }
        if (asset != null) {
            new BajaReasonFragment.DownloadImageTask(asset_img)
                    .execute(Constants.SERVER_URL + asset.getUrl_photo());
            asset_text.setText(asset.getType().getName());

        }


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
