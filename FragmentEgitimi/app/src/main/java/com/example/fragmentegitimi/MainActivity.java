package com.example.fragmentegitimi;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //şimdi aktivity içerisindeki fragmenti gösterelim.
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.mainActivityFragment,new KayitFragment(),"fragment")
                .setTransitionStyle(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
                .commit();
        //bu kodlar ile fragmentimizi activity içerisinde gösteriyoruz.
    }
}
