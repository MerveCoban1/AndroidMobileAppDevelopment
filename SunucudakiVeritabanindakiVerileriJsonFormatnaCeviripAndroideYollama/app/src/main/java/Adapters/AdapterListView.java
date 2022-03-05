package Adapters;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;


import com.example.sunucudakiveritabanindakiverilerijsonformatnaceviripandroideyollama.R;

import java.util.List;

import Model.Kullanici;

public class AdapterListView extends BaseAdapter {
    List<Kullanici> list;
    Context context;
    Activity activity;

    public AdapterListView(List<Kullanici> list, Context context,Activity activity) {
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
        convertView = LayoutInflater.from(context).inflate(R.layout.layout, parent, false);
        TextView id, isim, soyad;
        LinearLayout layoutLinear;
        id = (TextView) convertView.findViewById(R.id.id);
        isim = (TextView) convertView.findViewById(R.id.isim);
        soyad = (TextView) convertView.findViewById(R.id.soyad);
        id.setText(list.get(position).getId());   //bunlar string döndüğü için herhangi bir işleme gerek kalmıyor. Ama bunlara dikkat et.
        isim.setText(list.get(position).getIsim());
        soyad.setText(list.get(position).getSoyad());
        return convertView;
    }
}
