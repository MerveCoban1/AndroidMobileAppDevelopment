package com.example.fragmenttenfragmenteverigondermeders3;

import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentTransaction;

public class ChangeFragment {
    private Context context;

    //activity.xml içindeki oluşturduğumuz framelayoutları burada tanımladık.
    public ChangeFragment(Context context) {
        this.context = context;
    }

    public void change(Fragment fragment) {

        ((FragmentActivity) context).getSupportFragmentManager().beginTransaction()
                .replace(R.id.fm1Layout, fragment, "fragment")
                .setTransitionStyle(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
                .commit();
    }

    public void change2(Fragment fragment) {

        ((FragmentActivity) context).getSupportFragmentManager().beginTransaction()
                .replace(R.id.fm2Layout, fragment, "fragment")
                .setTransitionStyle(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
                .commit();
    }
    public void veriGonderBirdenIkiye(Fragment fragment,String deger) {
        Bundle bundle=new Bundle();
        bundle.putString("isim",deger);
        fragment.setArguments(bundle);

        ((FragmentActivity) context).getSupportFragmentManager().beginTransaction()
                .replace(R.id.fm2Layout, fragment, "fragment")
                .setTransitionStyle(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
                .commit();
    }

}
