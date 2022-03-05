package com.example.nbetieczane;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

import java.util.List;

import Models.EczaneDetay;

public class EczaneAdapter extends BaseAdapter {
    List<EczaneDetay> list;
    Context context;
    Activity activity;

    public EczaneAdapter(List<EczaneDetay> list, Context context,Activity activity) {
        this.list = list;
        this.context = context;
        this.activity=activity;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        convertView= LayoutInflater.from(context).inflate(R.layout.layout,parent,false);
        TextView eczaneIsmi,eczaneTelefon,eczaneAdres,eczaneFax,eczaneAdresTarif;
        Button eczaneHaritadaGoster,ara;
        eczaneIsmi=(TextView)convertView.findViewById(R.id.eczaneIsmi);
        eczaneTelefon=(TextView)convertView.findViewById(R.id.eczaneTelefon);
        eczaneAdres=(TextView)convertView.findViewById(R.id.eczaneAdres);
        eczaneFax=(TextView)convertView.findViewById(R.id.eczaneFax);
        eczaneAdresTarif=(TextView)convertView.findViewById(R.id.eczaneAdresTarif);
        eczaneHaritadaGoster=(Button)convertView.findViewById(R.id.eczaneHaritadaGoster);
        ara=(Button)convertView.findViewById(R.id.ara);
        eczaneIsmi.setText(list.get(position).getEczaneIsmı());
        eczaneTelefon.setText(list.get(position).getTelefon());
        eczaneAdres.setText(list.get(position).getAdres());
        eczaneFax.setText(list.get(position).getFax());
        eczaneAdresTarif.setText(list.get(position).getTarif());
        ara.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //arama işlemlerini intentlerle yapıyoruz.
                Intent intent=new Intent();
                intent.setData(Uri.parse("tel:"+list.get(position).getTelefon()));
                activity.startActivity(intent);
            }
        });


        return convertView;
    }
}
