package com.example.fragmenttenfragmenteverigondermeders3;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;


public class FM1 extends Fragment {
    View view;
    EditText editText;
    Button buton;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_f_m1, container, false);
        tanimla();
        buton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ChangeFragment changeFragment=new ChangeFragment(getContext());
                changeFragment.veriGonderBirdenIkiye(new FM2(),editText.getText().toString());
            }
        });
        return view;
    }

    public void tanimla() {
        editText = (EditText) view.findViewById(R.id.editText);
        buton = (Button) view.findViewById(R.id.buton);
    }
}
