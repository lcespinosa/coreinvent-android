package com.coresolutions.coreinvent.ui.main;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.LinearLayout;

import com.coresolutions.coreinvent.R;
import com.coresolutions.coreinvent.ui.alta.AltaActivity;
import com.coresolutions.coreinvent.ui.baja.BajaActivity;
import com.coresolutions.coreinvent.ui.movement.MovementActivity;

/**
 * A simple {@link Fragment} subclass.
 */
public class OperationFragment extends DialogFragment {
    private LinearLayout alta_layout;
    private LinearLayout baja_layout;
    private LinearLayout movement_layout;

    public OperationFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setStyle(STYLE_NO_TITLE, R.style.DialogTheme_transparent);
//        setCancelable(false);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_operation, container, false);
        if (getDialog() != null && getDialog().getWindow() != null) {
            getDialog().getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
            getDialog().getWindow().requestFeature(Window.FEATURE_NO_TITLE);
        }
        return view;

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        alta_layout = view.findViewById(R.id.alta_layout);
        baja_layout = view.findViewById(R.id.baja_layout);
        movement_layout = view.findViewById(R.id.movement_layout);
        alta_layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getContext(), AltaActivity.class);
                startActivity(i);
            }
        });

        baja_layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getContext(), BajaActivity.class);
                startActivity(i);
            }
        });
        movement_layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getContext(), MovementActivity.class);
                startActivity(i);
            }
        });
    }
}
