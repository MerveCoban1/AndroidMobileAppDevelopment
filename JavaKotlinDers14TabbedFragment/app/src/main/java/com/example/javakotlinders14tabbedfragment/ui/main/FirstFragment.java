package com.example.javakotlinders14tabbedfragment.ui.main;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import com.example.javakotlinders14tabbedfragment.R;



public class FirstFragment extends Fragment {
    PageViewModel pageViewModel;

    public static FirstFragment newInstance() {
        return new FirstFragment();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //aktiviteyi almak istediğimde framelerde
        Toast.makeText(getActivity(), "message", Toast.LENGTH_LONG).show();
        //ya da
        Toast.makeText(getActivity().getApplicationContext(), "message", Toast.LENGTH_LONG).show();
        //kullanabilirim
        pageViewModel = ViewModelProviders.of(requireActivity()).get(PageViewModel.class);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_first, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        EditText editText = view.findViewById(R.id.editTextFirstFragment);
        //editText'in içeriğinin değişip değişmediğini kontrol ediyoruz.
        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                //değişmeden önce
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                //text değişir değişmez
                pageViewModel.setName(s.toString());
            }

            @Override
            public void afterTextChanged(Editable s) {
                //değiştikten sonra
            }
        });
    }
}
