package com.coresolutions.coreinvent.ui.movement;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import com.coresolutions.coreinvent.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

/**
 * A simple {@link Fragment} subclass.
 */
public class MovementResultFragment extends Fragment {

    private FloatingActionButton ok;
    private FloatingActionButton cancel;
    private TextView resultText;
    private TextView message_text;
    private SharedPreferences settings;


    public MovementResultFragment() {
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_result, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ok = view.findViewById(R.id.ok);
        cancel = view.findViewById(R.id.cancel);
        resultText = view.findViewById(R.id.resultText);
        message_text = view.findViewById(R.id.message_text);
        settings = PreferenceManager.getDefaultSharedPreferences(getContext());
        String user_name = settings.getString("user_name", "");
        String result = getArguments().getString("result");

        resultText.setText(result);
        message_text.setText(user_name + ", ¿quieres realizar otra operación?");

        ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Navigation.findNavController(v).navigate(R.id.action_bajaResultFragment_to_tag_fragment);
            }
        });

        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().finish();
            }
        });

    }
}
