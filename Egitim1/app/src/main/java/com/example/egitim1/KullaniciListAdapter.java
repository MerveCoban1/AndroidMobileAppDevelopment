package com.example.egitim1;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

public class KullaniciListAdapter extends BaseAdapter {
    Context context;
    List<KullaniciModeli> list;
    public KullaniciListAdapter(List<KullaniciModeli> list, Context context){
        this.list=list;
        this.context=context;
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
        //burada oluşturduğumuz layout u tanımlatacağız.//değerleri bu adapter içinde atacağız
        View layout= LayoutInflater.from(context).inflate(R.layout.listview2,parent,false);
                //java classlarında contextler yoktur contextler activity classlarına özgüdür.
        //4 tane textview ı tanımlıycaz
        //java  classlarında findviewbyid yoktur onlar activitty classlarında vardır.
        //yukarda oluşturduğumuz layout nesnesine göre bu tanımlamalarımızı yapıyoruz.
        TextView isim=layout.findViewById(R.id.isim);
        TextView soyisim=layout.findViewById(R.id.soyisim);
        TextView yas=layout.findViewById(R.id.yas);
        TextView takim=layout.findViewById(R.id.takim);
        isim.setText(list.get(position).getIsim()); //position 1 se 1.listemizi al
        soyisim.setText(list.get(position).getSoyisim());
        yas.setText(list.get(position).getYas());
        takim.setText(list.get(position).getTakim());



        return layout;
    }
}
