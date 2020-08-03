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
import com.coresolutions.coreinvent.data.pojos.Center;
import com.coresolutions.coreinvent.data.pojos.Edifice;
import com.coresolutions.coreinvent.data.pojos.FindAssetPojo;
import com.coresolutions.coreinvent.data.pojos.Level;
import com.coresolutions.coreinvent.data.pojos.Notification;
import com.coresolutions.coreinvent.data.pojos.Space;
import com.coresolutions.coreinvent.ui.alta.AltaViewModel;
import com.coresolutions.coreinvent.ui.baja.BajaReasonFragment;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.InputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class MovementLocationFragment extends Fragment implements NotificationFragment.OnClickListener, NotificationFragment.OnCloseListener {

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
    private MovementRequestBody movementRequestBody;
    private CheckBox notificationCheckBox;

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
    public void onViewCreated(@NonNull final View view, @Nullable Bundle savedInstanceState) {
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

        notificationCheckBox = view.findViewById(R.id.notificationCheckBox);

        progressDialog = new ProgressDialog(getContext());
        progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progressDialog.setMessage("Cargando datos...");
        progressDialog.setCancelable(false);
        progressDialog.show();

        movementRequestBody = new MovementRequestBody();


        if (getArguments().getSerializable("asset") != null) {
            asset = (FindAssetPojo) getArguments().getSerializable("asset");
            movementRequestBody.setAssetId(asset.getId());
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

        notificationCheckBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    NotificationFragment notificationFragment = new NotificationFragment(token, MovementLocationFragment.this, MovementLocationFragment.this);
                    notificationFragment.show(getFragmentManager(), "notification");
                } else {
                    movementRequestBody.clearNotification();
                }
            }
        });

        forward_img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                progressDialog.show();
                altaViewModel.assetMovement(token, movementRequestBody);
            }
        });

        altaViewModel.getResponseBodyUnsubscription().observe(this, new Observer<HashMap<String, String>>() {
            @Override
            public void onChanged(HashMap<String, String> responseHashMap) {
                progressDialog.dismiss();
                if (!responseHashMap.containsKey("errors")) {
                    Bundle bundle = new Bundle();
                    bundle.putString("result", "Registro de traslado guardado con éxito");
                    Navigation.findNavController(view).navigate(R.id.action_movement_location_fragment_to_movementResultFragment, bundle);
                } else {
                    Toast.makeText(getContext(), responseHashMap.get("errors"), Toast.LENGTH_LONG).show();
                }
            }
        });


        center_dropdown.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Center center = (Center) center_dropdown.getAdapter().getItem(position);
                movementRequestBody.setCenter(center.getId());
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
                movementRequestBody.setEdificeId(edifice.getId());
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
                movementRequestBody.setLevel(level.getId());
                space_dropdown.clearListSelection();
                space_dropdown.setText("");
                space_dropdown.setAdapter(new ArrayAdapter<>(getContext(), R.layout.dropdown_menu_popup_item, level.getSpaces()));
            }
        });

        space_dropdown.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Space space = (Space) space_dropdown.getAdapter().getItem(position);
                movementRequestBody.setSpace(space.getId());
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

    @Override
    public void OnClickListener(int user_id, String description) {
        movementRequestBody.addNotifyUser(user_id);
        movementRequestBody.setNotifyText(description);

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


    public class MovementRequestBody implements Serializable {

        @SerializedName("asset_id")
        @Expose
        private Integer assetId;
        @SerializedName("center")
        @Expose
        private Integer center;
        @SerializedName("edifice_id")
        @Expose
        private Integer edificeId;
        @SerializedName("level")
        @Expose
        private Integer level;
        @SerializedName("space")
        @Expose
        private Integer space;
        @SerializedName("notify_users")
        @Expose
        private List<Integer> notifyUsers = null;
        @SerializedName("notify_text")
        @Expose
        private String notifyText;
        private final static long serialVersionUID = -8517564530565055380L;


        public MovementRequestBody() {
            notifyUsers = new ArrayList<>();
            notifyText = "";
        }

        public Integer getAssetId() {
            return assetId;
        }

        public void setAssetId(Integer assetId) {
            this.assetId = assetId;
        }

        public Integer getCenter() {
            return center;
        }

        public void setCenter(Integer center) {
            this.center = center;
        }

        public Integer getEdificeId() {
            return edificeId;
        }

        public void setEdificeId(Integer edificeId) {
            this.edificeId = edificeId;
        }

        public Integer getLevel() {
            return level;
        }

        public void setLevel(Integer level) {
            this.level = level;
        }

        public Integer getSpace() {
            return space;
        }

        public void setSpace(Integer space) {
            this.space = space;
        }

        public List<Integer> getNotifyUsers() {
            return notifyUsers;
        }

        public void setNotifyUsers(List<Integer> notifyUsers) {
            this.notifyUsers = notifyUsers;
        }

        public void addNotifyUser(int userId) {
            this.notifyUsers.add(userId);
        }

        public void clearNotification() {
            this.notifyUsers.clear();
            this.notifyText = "";
        }


        public String getNotifyText() {
            return notifyText;
        }

        public void setNotifyText(String notifyText) {
            this.notifyText = notifyText;
        }

    }
}
