package com.example.fragmentlerarasigecisders2;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

public class Fragment3 extends Fragment {
    View view;
    Button button;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_3, container, false);
        tanimla();
        return view;
    }

    public void tanimla() {
        button = (Button) view.findViewById(R.id.buton3);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                changeFragment();
            }
        });
    }
    public void changeFragment(){
        //fragment 1 e geçiş yapacagız
        //fragmentlerde context gönderebilmemiz için getContext() kullanmalıyız./activity göndereceksek getActivity() de kullanılıyor.
        ChangeFragment changeFragment=new ChangeFragment(getContext());
        changeFragment.change(new Fragment2());
    }
}
