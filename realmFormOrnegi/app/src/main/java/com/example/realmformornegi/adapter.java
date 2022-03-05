package com.example.realmformornegi;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

public class adapter  extends BaseAdapter {
    List<KisiBilgileri> list;
    Context context;

    public adapter(List<KisiBilgileri> list, Context context) {
        this.list = list;
        this.context = context;
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
        convertView= LayoutInflater.from(context).inflate(R.layout.layoutlistview,parent,false);
        TextView kullaniciAdiList=convertView.findViewById(R.id.kullaniciAdiList);
        TextView isimList=convertView.findViewById(R.id.isimList);
        TextView cinsiyetList=convertView.findViewById(R.id.cinsiyetList);
        TextView sifreList=convertView.findViewById(R.id.sifreList);
        kullaniciAdiList.setText(list.get(position).getKullaniciAdi());
        isimList.setText(list.get(position).getIsim());
        cinsiyetList.setText(list.get(position).getCinsiyet());
        sifreList.setText(list.get(position).getSifre());
        return convertView;
    }
}
