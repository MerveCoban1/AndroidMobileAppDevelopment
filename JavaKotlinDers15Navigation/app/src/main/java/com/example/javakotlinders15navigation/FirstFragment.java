package com.example.javakotlinders15navigation;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavDirections;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

public class FirstFragment extends Fragment {

    public FirstFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_first, container, false);
    }

    @Override
    public void onViewCreated(@NonNull final View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Button yolla=view.findViewById(R.id.yolla);
        yolla.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //şimdi aksiyonu çalıştırmak istiyorum. Yani ordaki ok nereyi gösteriyorsa oraya gitsin
                //NavDirections action=FirstFragmentDirections.actionFirstFragmentToSecondFragment();
                //normalde bu yukardakini kullanıyoruz ama veri yollarken bi seçeneğimiz daha var

                FirstFragmentDirections.ActionFirstFragmentToSecondFragment action=FirstFragmentDirections.actionFirstFragmentToSecondFragment();
                action.setAge(50);
                Navigation.findNavController(view).navigate(action);

            }
        });
    }
}
