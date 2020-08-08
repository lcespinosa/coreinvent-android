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
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

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
import com.coresolutions.coreinvent.data.pojos.Type;
import com.coresolutions.coreinvent.data.pojos.Unsubscription;
import com.coresolutions.coreinvent.data.pojos.UnsubscriptionRequestBody;
import com.coresolutions.coreinvent.data.pojos.UnsubscriptionVar;
import com.coresolutions.coreinvent.ui.alta.AltaViewModel;
import com.coresolutions.coreinvent.ui.main.DetailFragment;
import com.coresolutions.coreinvent.ui.movement.MovementLocationFragment;
import com.coresolutions.coreinvent.ui.movement.NotificationFragment;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import okhttp3.ResponseBody;

/**
 * A simple {@link Fragment} subclass.
 */
public class BajaReasonFragment extends Fragment implements NotificationFragment.OnClickListener, NotificationFragment.OnCloseListener {

    private FindAssetPojo asset;
    private UnsubscriptionVar unsubscriptionVar;
    private ImageView asset_img;
    private ImageView baja_buttom;
    private ImageView back_img;
    private TextView asset_text;
    private AutoCompleteTextView baja_reason;
    private AltaViewModel altaViewModel;
    private SharedPreferences settings;
    private ProgressDialog progressDialog;
    private List<Integer> notifyUsers;
    private String notifyText;
    private String token;
    private CheckBox notificationCheckBox;

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
    public void onViewCreated(@NonNull final View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        settings = PreferenceManager.getDefaultSharedPreferences(getContext());
        altaViewModel = ViewModelProviders.of(this).get(AltaViewModel.class);

        token = settings.getString("access_token", "");

        progressDialog = new ProgressDialog(getContext());
        progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progressDialog.setMessage("Cargando datos...");
        progressDialog.setCancelable(false);
        progressDialog.show();

        notificationCheckBox = view.findViewById(R.id.notificationCheckBox);

        notifyUsers = new ArrayList<>();
        notifyText = "";
        asset_img = view.findViewById(R.id.asset_img);
        asset_text = view.findViewById(R.id.asset_text);
        baja_reason = view.findViewById(R.id.baja_reason);
        baja_buttom = view.findViewById(R.id.baja_buttom);
        back_img = view.findViewById(R.id.back_img);

        altaViewModel.getUnsubscriptionResult().observe(this, new Observer<Unsubscription>() {
            @Override
            public void onChanged(Unsubscription unsubscription) {
                if (unsubscription != null) {
                    baja_reason.setAdapter(new ArrayAdapter<>(getContext(), R.layout.dropdown_menu_popup_item, unsubscription.getUnsubscriptionVars()));
                } else {
                    progressDialog.dismiss();
                    Toast.makeText(getContext(), "Se ha perdido la conexión con el servidor", Toast.LENGTH_LONG).show();
                }
            }
        });


        notificationCheckBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    NotificationFragment notificationFragment = new NotificationFragment(token, BajaReasonFragment.this, BajaReasonFragment.this);
                    notificationFragment.show(getFragmentManager(), "notification");
                } else {
                    notifyUsers.clear();
                    notifyText = "";
                }
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
        baja_reason.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                unsubscriptionVar = (UnsubscriptionVar) baja_reason.getAdapter().getItem(position);
            }
        });

        back_img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().onBackPressed();
            }
        });

        baja_buttom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                progressDialog.show();
                UnsubscriptionRequestBody unsubscriptionRequestBody = new UnsubscriptionRequestBody(String.valueOf(asset.getId()), String.valueOf(unsubscriptionVar.getId()), notifyUsers, notifyText);
                altaViewModel.assetUnSubscription(token, unsubscriptionRequestBody);
            }
        });

        altaViewModel.getResponseBodyUnsubscription().observe(this, new Observer<HashMap<String, String>>() {
            @Override
            public void onChanged(HashMap<String, String> hashMap) {
                if (hashMap != null) {
                    Bundle bundle = new Bundle();
                    if (hashMap.containsKey("errors")) {
                        Toast.makeText(getContext(), "Motivo de baja incorrecto para este bien", Toast.LENGTH_LONG).show();
                        progressDialog.dismiss();
                    } else {
                        bundle.putString("result", "Operación realizada correctamente");
                        progressDialog.dismiss();
                        Navigation.findNavController(view).navigate(R.id.action_reason_fragment_to_bajaResultFragment, bundle);
                    }


                }
            }
        });


    }

    @Override
    public void OnClickListener(int user_id, String description) {
        notifyUsers.add(user_id);
        notifyText = description;

    }

    @Override
    public void OnCloseListener() {
        notificationCheckBox.setChecked(false);
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
