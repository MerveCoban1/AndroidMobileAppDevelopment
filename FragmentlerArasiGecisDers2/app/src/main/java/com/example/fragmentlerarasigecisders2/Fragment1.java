package com.example.fragmentlerarasigecisders2;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

public class Fragment1 extends Fragment {
    View view;
    Button button;
    TextView gelenDeger;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_1, container, false);
        tanimla();
        if(getArguments()!=null){
            String isimDegeri=getArguments().getString("isim").toString();
            gelenDeger.setText(isimDegeri);
        }
        return view;
    }

    public void tanimla() {
        button = (Button) view.findViewById(R.id.buton1);
        gelenDeger = (TextView) view.findViewById(R.id.gelenDeger);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                changeFragment();
            }
        });
    }

    public void changeFragment() {
        //fragment 2 e geçiş yapacagız
        //fragmentlerde context gönderebilmemiz için getContext() kullanmalıyız./activity göndereceksek getActivity() de kullanılıyor.
        ChangeFragment changeFragment = new ChangeFragment(getContext());
        changeFragment.change(new Fragment2());
    }
}
