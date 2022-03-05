package com.example.fragmentlerarasigecisders2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {
    EditText editText;
    Button gonder;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tanimla();

        gonder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Fragment fragment=new Fragment1();  //hangi fragment açılacaksa onu atıyoruz.
                Bundle bundle=new Bundle();
                bundle.putString("isim",editText.getText().toString());
                fragment.setArguments(bundle);
                ChangeFragment changeFragment=new ChangeFragment(MainActivity.this);
                changeFragment.change(fragment);
            }
        });


    }
    public void ilkDers(){
        /*ChangeFragment changeFragment=new ChangeFragment(MainActivity.this);
        if(savedInstanceState==null){  //eğer ki ilk başta hiç fragmentimiz yoksa bir fragment atacağız.
            changeFragment.change(new Fragment1());
        }*/
    }
    public void tanimla(){
        editText=(EditText)findViewById(R.id.editText);
        gonder=(Button)findViewById(R.id.gonder);
    }
}
