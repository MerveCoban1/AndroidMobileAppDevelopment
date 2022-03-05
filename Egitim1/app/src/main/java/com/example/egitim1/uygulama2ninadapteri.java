package com.example.egitim1;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class uygulama2ninadapteri extends BaseAdapter {
    List<mesajModel> list;
    Context context;
    public uygulama2ninadapteri(List<mesajModel> list, Context context) {
        this.context=context;
        this.list=list;
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
        View layout= LayoutInflater.from(context).inflate(R.layout.ornekuygulama2ninlayoutu,parent,false);
        ImageView img=(ImageView) layout.findViewById(R.id.kisiResmi);
        TextView kisiIsmi=(TextView) layout.findViewById(R.id.kisiIsmi);
        TextView mesaj=(TextView) layout.findViewById(R.id.mesaj);
        img.setImageResource(list.get(position).getResimId());
        kisiIsmi.setText(list.get(position).getKisi());
        mesaj.setText(list.get(position).getMesajİcerik());


        return layout;
    }
}
