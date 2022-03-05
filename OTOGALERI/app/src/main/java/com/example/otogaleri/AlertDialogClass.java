package com.example.otogaleri;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;

import io.realm.Realm;
import io.realm.RealmResults;
import veritabaniTablolari.IlanlarVeritabaniTablosu;

public class AlertDialogClass extends Ilanlarim{
    Realm realm;
    public void ilanlarimAlertDialog(Activity activity, final String ilanNumarasi){
        LayoutInflater inflater=activity.getLayoutInflater();
        realm=Realm.getDefaultInstance();
        View view=inflater.inflate(R.layout.alert_dialog_icin,null);
        Button sil=(Button)view.findViewById(R.id.sil);
        Button cik=(Button)view.findViewById(R.id.cik);

        AlertDialog.Builder alert=new AlertDialog.Builder(activity);
        alert.setView(view);
        alert.setCancelable(false);
        final AlertDialog dialog=alert.create();
        Log.i("kaynak","1"+ilanNumarasi);
        cik.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.cancel();
            }
        });
        dialog.show();
        sil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                realm.beginTransaction();
                RealmResults<IlanlarVeritabaniTablosu> sonuc=realm.where(IlanlarVeritabaniTablosu.class).findAll();
                for(IlanlarVeritabaniTablosu ilan : sonuc){
                    if(ilan.getIlan_id().equals(ilanNumarasi)){
                        ilan.deleteFromRealm();
                        dialog.cancel();
                    }
                }
                //RealmResults<IlanlarVeritabaniTablosu> sonuc=realm9.where(IlanlarVeritabaniTablosu.class).findAll();
                realm.commitTransaction();
            }
        });
    }
}
