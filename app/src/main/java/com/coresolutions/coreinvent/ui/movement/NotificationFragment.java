package com.coresolutions.coreinvent.ui.movement;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.ImageView;
import android.widget.Toast;

import com.coresolutions.coreinvent.R;
import com.coresolutions.coreinvent.data.pojos.FamilyPojo;
import com.coresolutions.coreinvent.data.pojos.FindAssetPojo;
import com.coresolutions.coreinvent.data.pojos.User;
import com.coresolutions.coreinvent.ui.login.LoginViewModel;
import com.coresolutions.coreinvent.ui.login.LoginViewModelFactory;
import com.coresolutions.coreinvent.ui.main.AssetListAdapter;
import com.google.android.material.textfield.TextInputEditText;

import java.util.List;

public class NotificationFragment extends DialogFragment {

    public interface OnClickListener {
        void OnClickListener(int user_id, String description);
    }

    public interface OnCloseListener {
        void OnCloseListener();
    }

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "token";

    // TODO: Rename and change types of parameters
    private String mToken;
    private ImageView close_img;
    private AutoCompleteTextView user_dropdown;
    private TextInputEditText description;
    private ImageView forward_img;
    private ConstraintLayout loading;
    private int userId;


    private LoginViewModel loginViewModel;

    private NotificationFragment.OnClickListener onClickListener;
    private NotificationFragment.OnCloseListener onCloseListener;

    public NotificationFragment(String token, NotificationFragment.OnClickListener onClickListener, NotificationFragment.OnCloseListener onCloseListener) {
        mToken = token;
        this.onClickListener = onClickListener;
        this.onCloseListener = onCloseListener;
        userId = -1;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mToken = getArguments().getString(ARG_PARAM1);
        }
//        setStyle(STYLE_NO_TITLE, R.style.DialogTheme_transparent);
        setCancelable(false);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_notification, container, false);
        if (getDialog() != null && getDialog().getWindow() != null) {
            getDialog().getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
            getDialog().getWindow().requestFeature(Window.FEATURE_NO_TITLE);
        }
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        loginViewModel = ViewModelProviders.of(this, new LoginViewModelFactory())
                .get(LoginViewModel.class);

        close_img = view.findViewById(R.id.close_img);
        user_dropdown = view.findViewById(R.id.user_dropdown);
        description = view.findViewById(R.id.description);
        forward_img = view.findViewById(R.id.forward_img);
        loading = view.findViewById(R.id.loading);

        close_img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onCloseListener.OnCloseListener();
                dismiss();
            }
        });

        user_dropdown.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                User user = (User) user_dropdown.getAdapter().getItem(position);
                userId = user.getId();
            }
        });

        forward_img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (userId != -1 && !description.getText().toString().equals("")) {
                    onClickListener.OnClickListener(userId, description.getText().toString());
                    dismiss();
                } else {
                    Toast.makeText(getContext(), "Debe seleccionar un usuario e introducir una descripción", Toast.LENGTH_LONG).show();
                }
            }
        });


        loginViewModel.getUserResult().observe(this, new Observer<List<User>>() {
            @Override
            public void onChanged(List<User> users) {
                user_dropdown.setAdapter(new ArrayAdapter<>(getContext(), R.layout.dropdown_menu_popup_item, users));
                loading.setVisibility(View.GONE);
            }
        });

        loginViewModel.getAllUsers(mToken);


    }
}