package com.example.fragmentegitimi;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class KayitFragment extends Fragment {
    View view;
    EditText kadi;
    TextView kadiText;
    Button buton;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_kayit, container, false);
        tanimla();
        action();
        return view;
    }
    public void tanimla(){
        kadi=(EditText) view.findViewById(R.id.kadi);
        kadiText=(TextView) view.findViewById(R.id.kadiText);
        buton=(Button) view.findViewById(R.id.buton);
    }
    public void action(){
        buton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                kadiText.setText(kadi.getText().toString());
                kadi.setText("");
            }
        });
    }
}
