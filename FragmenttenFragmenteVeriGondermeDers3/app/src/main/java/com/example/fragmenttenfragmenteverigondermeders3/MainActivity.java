package com.example.fragmenttenfragmenteverigondermeders3;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ChangeFragment changeFragment=new ChangeFragment(MainActivity.this);
        changeFragment.change(new FM1());
        changeFragment.change2(new FM2());
    }
}
