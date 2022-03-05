package com.example.fragmenttenfragmenteverigondermeders3;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class FM2 extends Fragment {
    View view;
    TextView textViewIki;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_f_m2, container, false);
        textViewIki = (TextView) view.findViewById(R.id.textViewIki);
        if (getArguments() != null) {
            String veri = getArguments().getString("isim").toString();
            textViewIki.setText(veri);
        }

        return view;
    }
}
