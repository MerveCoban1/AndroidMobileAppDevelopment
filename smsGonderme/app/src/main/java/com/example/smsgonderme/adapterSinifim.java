package com.example.smsgonderme;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.List;

public class adapterSinifim extends BaseAdapter {
    List<modelSinifim> list;
    Context context;
    Activity activity;

    public adapterSinifim(List<modelSinifim> list, Context context,Activity activity) {
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
    public View getView(int position, View convertView, ViewGroup parent) {
        convertView= LayoutInflater.from(context).inflate(R.layout.layout,parent,false);
        final EditText mesajIcerik=(EditText) convertView.findViewById(R.id.mesajIcerik);
        TextView isim=(TextView) convertView.findViewById(R.id.isim);
        TextView tel=(TextView) convertView.findViewById(R.id.tel);
        Button gonder=(Button) convertView.findViewById(R.id.gonder);
        tel.setText(tel.getText()+" "+list.get(position).getTel());
        isim.setText(isim.getText()+" "+list.get(position).getIsim());
        final String telNo=list.get(position).getTel();
        gonder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String mesaj=mesajIcerik.getText().toString(); //bunu yazınca otomatik final yaptı.
                Intent intent=new Intent(Intent.ACTION_VIEW, Uri.parse("sms:"+telNo));
                intent.putExtra("sms_body",mesaj);
                activity.startActivity(intent);
            }
        });
        /*eger arama yapsın istiyorsak
        önce iznimizi alıyoruz manifest dosyasından sonra;

        gonder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Intent.ACTION_DIAL);
                intent.setData(Uri.parse("tel:"+telNo));
                activity.startActivity(intent);
            }
        });*/
        return convertView;
    }
}
